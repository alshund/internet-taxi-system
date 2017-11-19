package by.tr.epam.service;

import by.tr.epam.domain.AuthenticationData;

public interface RegistrationService {

    String signUp(AuthenticationData authenticationData);
}
