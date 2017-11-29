package by.tr.web.service;

import by.tr.web.domain.AuthenticationData;
import by.tr.web.service.exception.AuthenticationException;
import by.tr.web.service.exception.UserServiceException;

public interface AuthorizationService {

    String signIn(AuthenticationData data) throws AuthenticationException, UserServiceException;
}
