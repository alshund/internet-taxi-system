package by.tr.web.service;

import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;

public interface HashService {

    HashData formHashData(AuthenticationData authenticationData);
    String createPasswordHash(String password, String salt);
}
