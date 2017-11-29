package by.tr.web.dao;

import by.tr.web.dao.exception.NoSuchUserException;
import by.tr.web.dao.exception.SQLUserDAOException;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;

public interface UserDAO {

    String signUp(AuthenticationData data) throws SQLUserDAOException, NoSuchUserException;
    boolean isUserSignUp(String email) throws SQLUserDAOException;
    String getMatcherId(String email) throws SQLUserDAOException, NoSuchUserException;
    String getMatcherRole(String email) throws SQLUserDAOException, NoSuchUserException;
    HashData getMatcherHashData(String email) throws SQLUserDAOException, NoSuchUserException;
}
