package by.tr.web.service;

import by.tr.web.service.impl.AuthorizationServiceImpl;
import by.tr.web.service.impl.HashServiceImpl;
import by.tr.web.service.impl.RegistrationServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

//    private final HashService hashService = new HashServiceImpl();
    private final AuthorizationService authorizationService = new AuthorizationServiceImpl();
    private final RegistrationService registrationService = new RegistrationServiceImpl();

    private ServiceFactory() { }

    public static ServiceFactory getInstance() {
        return instance;
    }

//    public HashService getHashService() {
//        return hashService;
//    }

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }
}
