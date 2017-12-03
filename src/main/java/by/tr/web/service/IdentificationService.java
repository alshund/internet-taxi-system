package by.tr.web.service;

import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.DriverApplication;
import by.tr.web.service.exception.AuthenticationException;
import by.tr.web.service.exception.UserServiceException;

public interface IdentificationService {

    String signIn(AuthenticationData authenticationData) throws AuthenticationException, UserServiceException;
    void signUp(AuthenticationData authenticationData) throws AuthenticationException, UserServiceException;
}
