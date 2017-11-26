package by.tr.web.dao.impl;

import by.tr.web.dao.UserDAO;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;
import by.tr.web.domain.TagAttributes;

import java.sql.*;
import java.util.ResourceBundle;

public class SQLUserDAO implements UserDAO {
    private Connection connection;
    private PreparedStatement statement;

    @Override
    public String signUp(AuthenticationData data) {

        ResourceBundle bundle = ResourceBundle.getBundle(DBBundleKeys.BUNDLE_NAME);
        try {
            openConnection(bundle);
            insertUser(data, bundle.getString(DBBundleKeys.INSERT_USER));
            return formMatcherRole(data.getEmail(), bundle);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getMatcherId(String email) {

        ResourceBundle bundle = ResourceBundle.getBundle(DBBundleKeys.BUNDLE_NAME);
        try {
            openConnection(bundle);
            return formMatcherId(email, bundle);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; //TODO throw Exception
    }

    @Override
    public String getMatcherRole(String email) {

        ResourceBundle bundle = ResourceBundle.getBundle(DBBundleKeys.BUNDLE_NAME);
        try {
            openConnection(bundle);
            return formMatcherRole(email, bundle);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public HashData getMatcherHashData(String email) {

        ResourceBundle bundle = ResourceBundle.getBundle(DBBundleKeys.BUNDLE_NAME);
        try {
            openConnection(bundle);
            return formMatcherHashData(email, bundle);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; //TODO throw Exception
    }

    private void openConnection(ResourceBundle bundle) throws SQLException, ClassNotFoundException {

        Class.forName(bundle.getString(DBBundleKeys.FOR_NAME));
        connection = DriverManager.getConnection(bundle.getString(DBBundleKeys.RIDER_URL),
                                                 bundle.getString(DBBundleKeys.USER),
                                                 bundle.getString(DBBundleKeys.PASS));
    }

    private void closeAll() throws SQLException {

        statement.close();
        connection.close();
    }

    private void insertUser(AuthenticationData data, String query) throws SQLException {

        statement = connection.prepareStatement(query);
        statement.setString(1, data.getEmail());
        statement.setString(2, data.getHashData().getPasswordHash());
        statement.setString(3, data.getHashData().getSalt());
        statement.setString(4, data.getRole());
        statement.executeUpdate();
    }

    private String formMatcherId(String email, ResourceBundle bundle) throws SQLException {

        ResultSet resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_ID));
        if (resultSet.next()) {
            return resultSet.getString(TagAttributes.id.name());
        }
        return null;
    }

    private String formMatcherRole(String email, ResourceBundle bundle) throws SQLException {

        ResultSet resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_ROLE));
        if (resultSet.next()) {
            return resultSet.getString(TagAttributes.role.name());
        }
        return null;
    }

    private HashData formMatcherHashData(String email, ResourceBundle bundle) throws SQLException {

        ResultSet resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_HASH_DATA));
        if (resultSet.next()) {
            return new HashData(resultSet.getString(TagAttributes.passwordHash.name()),
                                resultSet.getString(TagAttributes.salt.name()));
        }
        return null;
    }

    private ResultSet getMatcherParameter(String authenticationParameter, String query) throws SQLException {

        statement = connection.prepareStatement(query);
        statement.setString(1, authenticationParameter);
        return statement.executeQuery();
    }

}
