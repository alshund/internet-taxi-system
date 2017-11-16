package by.tr.epam.service.impl;

import by.tr.epam.dao.DAOFactory;
import by.tr.epam.dao.RiderDAO;
import by.tr.epam.domain.Rider;
import by.tr.epam.domain.SignInApplication;
import by.tr.epam.service.RiderService;

public class RiderServiceImpl implements RiderService {
    private RiderDAO riderDAO;

    public RiderServiceImpl() {

        DAOFactory instance = DAOFactory.getInstance();
        riderDAO = instance.getRiderDAO();
    }

    @Override
    public boolean signUp(Rider rider) {

        if (!riderDAO.isEmailSignUp(rider.getEmail())) {
            riderDAO.insertRider(rider);
            return true;
        }
        return false;
    }

    @Override
    public boolean signIn(SignInApplication application) {


        return false;
    }
}
