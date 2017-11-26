package by.tr.web.service.impl;

import by.tr.web.dao.DAOFactory;
import by.tr.web.dao.UserDAO;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;
import by.tr.web.service.HashService;
import by.tr.web.service.RegistrationService;
import by.tr.web.service.ServiceFactory;

public class RegistrationServiceImpl implements RegistrationService {
    private HashService hashService = new HashServiceImpl();
    private UserDAO userDAO;

    public RegistrationServiceImpl() {

        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @Override
    public String signUp(AuthenticationData authenticationData) {

        if (userDAO.getMatcherId(authenticationData.getEmail()) == null) {
            HashData hashData = hashService.formHashData(authenticationData);
            authenticationData.setHashData(hashData);
            return userDAO.signUp(authenticationData);
        }
        return null;
    }
}
