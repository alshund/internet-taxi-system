package by.tr.web.service.impl;

import by.tr.web.dao.DAOFactory;
import by.tr.web.dao.UserDAO;
import by.tr.web.dao.exception.NoSuchUserException;
import by.tr.web.dao.exception.SQLUserDAOException;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.DriverApplication;
import by.tr.web.domain.HashData;
import by.tr.web.service.IdentificationService;
import by.tr.web.service.exception.AuthenticationException;
import by.tr.web.service.exception.UserServiceException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class IdentificationServiceImpl implements IdentificationService {
    private final String algorithm = "SHA-1";
    private final int radix = 16;

    private UserDAO userDAO;

    public IdentificationServiceImpl() {

        DAOFactory instance = DAOFactory.getInstance();
        userDAO = instance.getUserDAO();
    }

    @Override
    public String signIn(AuthenticationData authenticationData) throws AuthenticationException, UserServiceException {

        try {
            HashData hashData = userDAO.getMatcherHashData(authenticationData.getEmail());
            String passwordHash = createPasswordHash(authenticationData.getPassword(), hashData.getSalt());
            if (passwordHash.equals(hashData.getPasswordHash())) {
                return userDAO.getMatcherRole(authenticationData.getEmail());
            } else {

                throw new AuthenticationException("Wrong PASSWORD: " + authenticationData.getPassword());
            }
        } catch (SQLUserDAOException e) {

            throw new UserServiceException("Exception during authentication data search", e);
        } catch (NoSuchUserException e) {

            throw new AuthenticationException("EMAIL doesn't registered: " + authenticationData.getEmail(), e);
        }
    }

    @Override
    public void signUp(AuthenticationData authenticationData) throws AuthenticationException, UserServiceException {

        try {
            if (!userDAO.isSignUp(authenticationData.getEmail())) {
                HashData hashData = formHashData(authenticationData);
                authenticationData.setHashData(hashData);
                userDAO.signUpRider(authenticationData);
            } else {

                throw new AuthenticationException("Email duplicate: " + authenticationData.getEmail());
            }
        } catch (SQLUserDAOException e) {

            throw new UserServiceException("Exception during recording: " + authenticationData.getEmail(), e);
        }
    }

    private HashData formHashData(AuthenticationData authenticationData) {

        HashData hashData = new HashData();
        hashData.setSalt(generateSalt(authenticationData.getPassword().length()));
        hashData.setPasswordHash(createPasswordHash(authenticationData.getPassword(), hashData.getSalt()));
        return hashData;
    }

    private String createPasswordHash(String password, String salt) {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update((password + salt).getBytes());
            return new BigInteger(digest.digest()).toString(radix);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateSalt(int passwordHashLength) {

        Random random = new SecureRandom();
        byte bytes[] = new byte[passwordHashLength];
        random.nextBytes(bytes);
        return new BigInteger(bytes).toString(radix);
    }
}
