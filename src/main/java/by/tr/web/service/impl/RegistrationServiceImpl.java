package by.tr.web.service.impl;

import by.tr.web.dao.DAOFactory;
import by.tr.web.dao.UserDAO;
import by.tr.web.dao.exception.NoSuchUserException;
import by.tr.web.dao.exception.SQLUserDAOException;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;
import by.tr.web.service.HashService;
import by.tr.web.service.RegistrationService;
import by.tr.web.service.exception.AuthenticationException;
import by.tr.web.service.exception.UserServiceException;

public class RegistrationServiceImpl implements RegistrationService {
    private HashService hashService = new HashServiceImpl();
    private UserDAO userDAO;

    public RegistrationServiceImpl() {

        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @Override
    public String signUp(AuthenticationData authenticationData) throws AuthenticationException, UserServiceException {

        try {
            if (!userDAO.isUserSignUp(authenticationData.getEmail())) {
                HashData hashData = hashService.formHashData(authenticationData);
                authenticationData.setHashData(hashData);
                return userDAO.signUp(authenticationData);
            } else {
                throw new AuthenticationException("Email duplicate: " + authenticationData.getEmail());
            }
        } catch (SQLUserDAOException e) {

            throw new UserServiceException("Exception during recording: " + authenticationData.getEmail(), e);
        } catch (NoSuchUserException e) {

            throw new UserServiceException("Exception during user recording: " + authenticationData.getEmail(), e);
        }
    }

    @Override
    public void sendDriverApplication() {


    }


}
