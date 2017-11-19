package by.tr.epam.dao;

import by.tr.epam.domain.AuthenticationData;
import by.tr.epam.domain.HashData;

public interface UserDAO {

    String signUp(AuthenticationData data);
    String getMatcherId(String email);
    String getMatcherRole(String email);
    HashData getMatcherHashData(String email);
}
