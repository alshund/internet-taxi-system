package by.tr.web.service.impl;

import by.tr.web.dao.DAOFactory;
import by.tr.web.dao.UserDAO;
import by.tr.web.dao.exception.NoSuchUserException;
import by.tr.web.dao.exception.SQLUserDAOException;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;
import by.tr.web.service.AuthorizationService;
import by.tr.web.service.HashService;
import by.tr.web.service.exception.AuthenticationException;
import by.tr.web.service.exception.UserServiceException;

public class AuthorizationServiceImpl implements AuthorizationService {
    private HashService hashService = new HashServiceImpl();
    private UserDAO userDAO;

    public AuthorizationServiceImpl() {

        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @Override
    public String signIn(AuthenticationData authenticationData) throws AuthenticationException, UserServiceException {

        try {
            HashData hashData = userDAO.getMatcherHashData(authenticationData.getEmail());
            String passwordHash = hashService.createPasswordHash(authenticationData.getPassword(), hashData.getSalt());
            if (passwordHash.equals(hashData.getPasswordHash())) {
                return userDAO.getMatcherRole(authenticationData.getEmail());
            } else {
                throw new AuthenticationException("Incorrect authorization parameters");
            }
        } catch (SQLUserDAOException e) {

            throw new UserServiceException("Exception during authentication data search", e);
        } catch (NoSuchUserException e) {

            throw new AuthenticationException("Incorrect authorization parameters", e);
        }
    }
}
