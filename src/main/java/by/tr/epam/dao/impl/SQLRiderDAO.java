package by.tr.epam.dao.impl;

import by.tr.epam.dao.RiderDAO;
import by.tr.epam.domain.Rider;
import by.tr.epam.domain.SignInApplication;

import java.sql.*;
import java.util.ResourceBundle;

public class SQLRiderDAO implements RiderDAO {
    private Connection connection;
    private PreparedStatement statement;

    @Override
    public void insertRider(Rider rider) {

        ResourceBundle bundle = ResourceBundle.getBundle(BundleKeys.BUNDLE_NAME);
        try {
            openConnection(bundle);
            insertRiderInToDB(rider, bundle);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isEmailSignUp(String email) {

        ResourceBundle bundle = ResourceBundle.getBundle(BundleKeys.BUNDLE_NAME);
        try {
            openConnection(bundle);
            return isIdMapped(email, bundle.getString(BundleKeys.SELECT_MATCHER_ID));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; //TODO throw Exception
    }

    @Override
    public String getMatcherPassword(String email) {

        ResourceBundle bundle = ResourceBundle.getBundle(BundleKeys.BUNDLE_NAME);
        try {
            openConnection(bundle);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null; //TODO throw Exception
    }

    private void openConnection(ResourceBundle bundle) throws SQLException, ClassNotFoundException {

        Class.forName(bundle.getString(BundleKeys.FOR_NAME));
        connection = DriverManager.getConnection(bundle.getString(BundleKeys.RIDER_URL),
                                                 bundle.getString(BundleKeys.USER),
                                                 bundle.getString(BundleKeys.PASS));
    }

    private void closeAll() throws SQLException {

        statement.close();
        connection.close();
    }

    private void insertRiderInToDB(Rider rider, ResourceBundle bundle) throws SQLException {

        statement = connection.prepareStatement(bundle.getString(BundleKeys.INSERT_RIDER));
        statement.setString(1, rider.getFirstName());
        statement.setString(2, rider.getLastName());
        statement.setString(3, rider.getPhoneNumber());
        statement.setString(4, rider.getEmail());
        statement.setString(5, rider.getPassword());
        statement.executeUpdate();
    }

    private boolean isIdMapped(String authenticationParameter, String query) throws SQLException {

        statement = connection.prepareStatement(query);
        statement.setString(1, authenticationParameter);
        return statement.execute();
    }

}
