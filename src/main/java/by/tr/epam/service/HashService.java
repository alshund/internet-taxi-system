package by.tr.epam.service;

import by.tr.epam.domain.AuthenticationData;
import by.tr.epam.domain.HashData;

public interface HashService {

    HashData formHashData(AuthenticationData authenticationData);
    String createPasswordHash(String password, String salt);
}
