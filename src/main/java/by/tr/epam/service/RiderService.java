package by.tr.epam.service;

import by.tr.epam.domain.SignInApplication;
import by.tr.epam.domain.Rider;

public interface RiderService {

    boolean signUp(Rider rider);
    boolean signIn(SignInApplication application);

}
