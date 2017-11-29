package by.tr.web.service;

import by.tr.web.domain.AuthenticationData;
import by.tr.web.service.exception.AuthenticationException;
import by.tr.web.service.exception.UserServiceException;

public interface RegistrationService {

    String signUp(AuthenticationData authenticationData) throws AuthenticationException, UserServiceException;
}
