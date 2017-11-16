package by.tr.epam.dao;

import by.tr.epam.domain.Rider;

public interface RiderDAO {

    void insertRider(Rider rider);
    boolean isEmailSignUp(String email);
    String getMatcherPassword(String email);
}
