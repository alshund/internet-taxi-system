package by.tr.web.service;

import by.tr.web.service.impl.IdentificationServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final IdentificationService identificationService = new IdentificationServiceImpl();

    private ServiceFactory() { }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public IdentificationService getIdentificationService() {
        return identificationService;
    }
}
