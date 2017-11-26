package by.tr.web.service.impl;

import by.tr.web.dao.DAOFactory;
import by.tr.web.dao.UserDAO;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;
import by.tr.web.service.AuthorizationService;
import by.tr.web.service.HashService;

public class AuthorizationServiceImpl implements AuthorizationService {
    private HashService hashService = new HashServiceImpl();
    private UserDAO userDAO;

    public AuthorizationServiceImpl() {

        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @Override
    public String signIn(AuthenticationData authenticationData) {

        HashData hashData = userDAO.getMatcherHashData(authenticationData.getEmail());
        String passwordHash = hashService.createPasswordHash(authenticationData.getPassword(), hashData.getSalt());
        System.out.println(passwordHash);
        if (passwordHash.equals(hashData.getPasswordHash())) {
            return userDAO.getMatcherRole(authenticationData.getEmail());
        }
        return null;
    }
}
