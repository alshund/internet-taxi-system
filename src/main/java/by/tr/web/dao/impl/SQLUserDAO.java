package by.tr.web.dao.impl;

import by.tr.web.dao.UserDAO;
import by.tr.web.dao.exception.ConnectionPoolException;
import by.tr.web.dao.exception.NoSuchUserException;
import by.tr.web.dao.exception.SQLUserDAOException;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.DriverApplication;
import by.tr.web.domain.HashData;
import by.tr.web.domain.TagAttributes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SQLUserDAO implements UserDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private ResourceBundle bundle = ResourceBundle.getBundle(DBBundleKeys.BUNDLE_NAME);

    @Override
    public void signUpRider(AuthenticationData authenticationData) throws SQLUserDAOException {

        try {
            connection = connectionPool.getConnection();
            String query = bundle.getString(DBBundleKeys.INSERT_RIDER_ID);
            insertUser(authenticationData, query);
        } catch (ConnectionPoolException e) {

            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } catch (SQLException e) {

            throw new SQLUserDAOException("Unable to insert rider", e);
        } finally {

            close();
        }
    }

    @Override
    public void signUpDriver(AuthenticationData authenticationData) throws SQLUserDAOException {

        try {
            connection = connectionPool.getConnection();
            String query = bundle.getString(DBBundleKeys.INSERT_DRIVER_ID);
            insertUser(authenticationData, query);
        } catch (ConnectionPoolException e) {

            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } catch (SQLException e) {

            throw new SQLUserDAOException("Unable to insert driver", e);
        } finally {

            close();
        }
    }

    @Override
    public void signUpApplication(DriverApplication application) throws SQLUserDAOException {

        try {
            connection = connectionPool.getConnection();
            executeInsert(application);
        } catch (ConnectionPoolException e) {

            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } catch (SQLException e) {

            throw new SQLUserDAOException("Unable to insert driver application", e);
        } finally {

            close();
        }
    }

    @Override
    public boolean isSignUp(String email) throws SQLUserDAOException {

        try {
            connection = connectionPool.getConnection();
            String query = bundle.getString(DBBundleKeys.SELECT_MATCHER_ID);
            ResultSet resultSet = getMatcherParameter(email, query);
            return resultSet.isBeforeFirst();
        } catch (ConnectionPoolException e) {

            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } catch (SQLException e) {

            throw new SQLUserDAOException("Unable to insert driver application", e);
        } finally {

            close();
        }
    }


    @Override
    public String getMatcherId(String email) throws SQLUserDAOException, NoSuchUserException {

        try {
            connection = connectionPool.getConnection();
            return formMatcherId(email);
        } catch (ConnectionPoolException e) {
            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } finally {
            close();
        }
    }

    @Override
    public String getMatcherRole(String email) throws SQLUserDAOException, NoSuchUserException {

        try {
            connection = connectionPool.getConnection();
            return formMatcherRole(email);
        } catch (ConnectionPoolException e) {

            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } catch (SQLException e) {

            throw new SQLUserDAOException("Unable to select role", e);
        } finally {
            close();
        }
    }

    @Override
    public HashData getMatcherHashData(String email) throws SQLUserDAOException, NoSuchUserException {

        try {
            connection = connectionPool.getConnection();
            return formMatcherHashData(email);
        } catch (ConnectionPoolException e) {
            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } finally {
            close();
        }
    }

    private void close() throws SQLUserDAOException {

        try {
            connectionPool.closeConnection(connection, statement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new SQLUserDAOException("Connection pool can not close connection/statement/resultSet", e);
        }
    }

    private void insertUser(AuthenticationData authenticationData, String query) throws SQLException {

        connection.setAutoCommit(false);

        executeInsert(authenticationData);
        statement = connection.prepareStatement(query);
        statement.executeUpdate();

        connection.commit();
    }

    private void executeInsert(AuthenticationData authenticationData) throws SQLException {

        String query = bundle.getString(DBBundleKeys.INSERT_USER);
        statement = connection.prepareStatement(query);
        setStatementParameters(authenticationData);
        statement.executeUpdate();
    }

    private void setStatementParameters(AuthenticationData authenticationData) throws SQLException {

        statement.setString(1, authenticationData.getEmail());
        statement.setString(2, authenticationData.getHashData().getPasswordHash());
        statement.setString(3, authenticationData.getHashData().getSalt());
        statement.setString(4, authenticationData.getRole());
    }

    private void executeInsert(DriverApplication application) throws SQLException {

        String query = bundle.getString(DBBundleKeys.INSERT_DRIVER_APPLICATION);
        statement = connection.prepareStatement(query);
        setStatementParameters(application);
        statement.executeUpdate();
    }

    private void setStatementParameters(DriverApplication application) throws SQLException {

        statement.setString(1, application.getEmail());
        statement.setString(2, application.getFirstName());
        statement.setString(3, application.getLastName());
        statement.setString(4, application.getPatronymic());
        statement.setString(5, application.getPhoneNumber());
        statement.setBinaryStream(6, application.getDriverLicense());
        statement.setBinaryStream(7, application.getPassport());
    }

    private String formMatcherId(String email) throws NoSuchUserException, SQLUserDAOException {

        try {
            resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_ID));
            if (resultSet.next()) {
                return resultSet.getString(TagAttributes.ID.name().toLowerCase());
            } else {
                throw new NoSuchUserException("EMAIL doesn't registered: " + email);
            }
        } catch (SQLException e) {
            throw new SQLUserDAOException("Unable to form matcher ID", e);
        }
    }

    private String formMatcherRole(String email) throws SQLUserDAOException, NoSuchUserException, SQLException {

        resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_ROLE));
        if (resultSet.next()) {
            return resultSet.getString(TagAttributes.ROLE.name().toLowerCase());
        } else {
            throw new NoSuchUserException("EMAIL doesn't registered: " + email);
        }
    }

    private HashData formMatcherHashData(String email) throws NoSuchUserException, SQLUserDAOException {

        try {
            resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_HASH_DATA));
            if (resultSet.next()) {
                return new HashData(resultSet.getString(TagAttributes.PASSWORD_HASH.name().toLowerCase()),
                                    resultSet.getString(TagAttributes.SALT.name().toLowerCase()));
            } else {
                throw new NoSuchUserException("EMAIL doesn't registered: " + email);
            }
        } catch (SQLException e) {
            throw new SQLUserDAOException("Unable to form matcher HASH DATA", e);
        }
    }

    private ResultSet getMatcherParameter(String authenticationParameter, String query) throws SQLException {

        statement = connection.prepareStatement(query);
        statement.setString(1, authenticationParameter);
        return statement.executeQuery();
    }

    private boolean isParameterMatcher(String authenticationParameter, String query) throws SQLException {

        statement = connection.prepareStatement(query);
        statement.setString(1, authenticationParameter);
        return statement.executeQuery().next();
    }
}
