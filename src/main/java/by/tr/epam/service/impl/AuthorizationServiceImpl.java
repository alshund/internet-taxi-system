package by.tr.epam.service.impl;

import by.tr.epam.dao.DAOFactory;
import by.tr.epam.dao.UserDAO;
import by.tr.epam.domain.AuthenticationData;
import by.tr.epam.domain.HashData;
import by.tr.epam.service.AuthorizationService;
import by.tr.epam.service.HashService;
import by.tr.epam.service.ServiceFactory;

public class AuthorizationServiceImpl implements AuthorizationService {
    private HashService hashService;
    private UserDAO userDAO;

    public AuthorizationServiceImpl() {

        hashService = ServiceFactory.getInstance().getHashService();
        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @Override
    public String signIn(AuthenticationData authenticationData) {

        HashData hashData = userDAO.getMatcherHashData(authenticationData.getEmail());
        String passwordHash = hashService.createPasswordHash(authenticationData.getPassword(), hashData.getSalt());
        if (passwordHash.equals(hashData.getPasswordHash())) {
            return userDAO.getMatcherRole(authenticationData.getEmail());
        }
        return null;
    }
}
