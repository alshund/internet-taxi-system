package by.tr.web.dao.impl;

import by.tr.web.dao.UserDAO;
import by.tr.web.dao.exception.ConnectionPoolException;
import by.tr.web.dao.exception.NoSuchUserException;
import by.tr.web.dao.exception.SQLUserDAOException;
import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;
import by.tr.web.domain.TagAttributes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SQLUserDAO implements UserDAO {
    private ConnectionPool instance = ConnectionPool.getInstance();
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private ResourceBundle bundle = ResourceBundle.getBundle(DBBundleKeys.BUNDLE_NAME);

    @Override
    public String signUp(AuthenticationData data) throws SQLUserDAOException, NoSuchUserException {

        try {
            connection = instance.getConnection();
            String query = bundle.getString(DBBundleKeys.INSERT_USER);
            statement = connection.prepareStatement(query);
            insertUser(data);
            return formMatcherRole(data.getEmail());
        } catch (ConnectionPoolException e) {

            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } catch (SQLException e) {

            throw new SQLUserDAOException("Unable to prepare insert statement", e);
        } finally {
            close();
        }
    }

    @Override
    public boolean isUserSignUp(String email) throws SQLUserDAOException {

        try {
            connection = instance.getConnection();
            String query = bundle.getString(DBBundleKeys.SELECT_MATCHER_ID);
            return isParameterMatcher(email, query);
        } catch (ConnectionPoolException e) {

            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } catch (SQLException e) {

            throw new SQLUserDAOException("Unable to prepare insert statement", e);
        } finally {
            close();
        }
    }

    @Override
    public String getMatcherId(String email) throws SQLUserDAOException, NoSuchUserException {

        try {
            connection = instance.getConnection();
            return formMatcherId(email);
        } catch (ConnectionPoolException e) {
            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } finally {
            close();
        }
    }

    @Override
    public String getMatcherRole(String email) throws SQLUserDAOException, NoSuchUserException {

        bundle = ResourceBundle.getBundle(DBBundleKeys.BUNDLE_NAME);
        try {
            connection = instance.getConnection();
            return formMatcherRole(email);
        } catch (ConnectionPoolException e) {
            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } finally {
            close();
        }
    }

    @Override
    public HashData getMatcherHashData(String email) throws SQLUserDAOException, NoSuchUserException {

        try {
            connection = instance.getConnection();
            return formMatcherHashData(email);
        } catch (ConnectionPoolException e) {
            throw new SQLUserDAOException("Connection pool can not return connection", e);
        } finally {
            close();
        }
    }

    private void close() throws SQLUserDAOException {

        try {
            instance.closeConnection(connection, statement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new SQLUserDAOException("Connection pool can not close connection/statement/resultSet", e);
        }
    }

    private int insertUser(AuthenticationData data) throws SQLUserDAOException {

        try {
            statement.setString(1, data.getEmail());
            statement.setString(2, data.getHashData().getPasswordHash());
            statement.setString(3, data.getHashData().getSalt());
            statement.setString(4, data.getRole());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLUserDAOException("Unable to insert user into DB");
        }
    }

    private String formMatcherId(String email) throws NoSuchUserException, SQLUserDAOException {

        try {
            resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_ID));
            if (resultSet.next()) {
                return resultSet.getString(TagAttributes.id.name());
            } else {
                throw new NoSuchUserException("This email doesn't registered");
            }
        } catch (SQLException e) {
            throw new SQLUserDAOException("Unable to form matcher id");
        }
    }

    private String formMatcherRole(String email) throws SQLUserDAOException, NoSuchUserException {

        try {
            resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_ROLE));
            if (resultSet.next()) {
                return resultSet.getString(TagAttributes.role.name());
            } else {
                throw new NoSuchUserException("This email doesn't registered");
            }
        } catch (SQLException e) {
            throw new SQLUserDAOException("Unable to form matcher role");
        }
    }

    private HashData formMatcherHashData(String email) throws NoSuchUserException, SQLUserDAOException {

        try {
            resultSet = getMatcherParameter(email, bundle.getString(DBBundleKeys.SELECT_MATCHER_HASH_DATA));
            if (resultSet.next()) {
                return new HashData(resultSet.getString(TagAttributes.passwordHash.name()),
                                    resultSet.getString(TagAttributes.salt.name()));
            } else {
                throw new NoSuchUserException("This email doesn't registered");
            }
        } catch (SQLException e) {
            throw new SQLUserDAOException("Unable to form matcher hash data");
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
