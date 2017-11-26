package by.tr.web.service;

import by.tr.web.domain.AuthenticationData;

public interface AuthorizationService {

    String signIn(AuthenticationData data);
}
