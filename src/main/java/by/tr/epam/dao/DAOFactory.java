package by.tr.epam.dao;

import by.tr.epam.dao.impl.SQLRiderDAO;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    public final RiderDAO riderDAO = new SQLRiderDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public RiderDAO getRiderDAO() {
        return riderDAO;
    }
}
