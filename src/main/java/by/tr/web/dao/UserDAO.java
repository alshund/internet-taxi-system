package by.tr.web.dao;

import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;

public interface UserDAO {

    String signUp(AuthenticationData data);
    String getMatcherId(String email);
    String getMatcherRole(String email);
    HashData getMatcherHashData(String email);
}
