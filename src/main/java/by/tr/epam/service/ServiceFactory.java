package by.tr.epam.service;

import by.tr.epam.service.impl.RiderServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final RiderService riderService = new RiderServiceImpl();

    private ServiceFactory() { }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public RiderService getRiderService() {
        return riderService;
    }
}
