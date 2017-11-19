package by.tr.epam.service.impl;

import by.tr.epam.dao.DAOFactory;
import by.tr.epam.dao.UserDAO;
import by.tr.epam.domain.AuthenticationData;
import by.tr.epam.domain.HashData;
import by.tr.epam.service.HashService;
import by.tr.epam.service.RegistrationService;
import by.tr.epam.service.ServiceFactory;

public class RegistrationServiceImpl implements RegistrationService {
    private HashService hashService;
    private UserDAO userDAO;

    public RegistrationServiceImpl() {

        hashService = ServiceFactory.getInstance().getHashService();
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
