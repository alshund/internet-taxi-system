package by.tr.web.dao;

import by.tr.web.dao.exception.NoSuchUserException;
import by.tr.web.dao.exception.SQLUserDAOException;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.DriverApplication;
import by.tr.web.domain.HashData;

public interface UserDAO {

    void signUpRider(AuthenticationData authenticationData) throws SQLUserDAOException;
    void signUpDriver(AuthenticationData authenticationData) throws SQLUserDAOException;
    void signUpApplication(DriverApplication driverApplication) throws SQLUserDAOException;

    boolean isSignUp(String email) throws SQLUserDAOException;
    String getMatcherId(String email) throws SQLUserDAOException, NoSuchUserException;// не уверена, что эти методы должны быть методами публичного интерфейса
    String getMatcherRole(String email) throws SQLUserDAOException, NoSuchUserException;
    HashData getMatcherHashData(String email) throws SQLUserDAOException, NoSuchUserException;
}
