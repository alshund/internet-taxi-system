package by.tr.epam.service;

import by.tr.epam.domain.AuthenticationData;

public interface AuthorizationService {

    String signIn(AuthenticationData data);
}
