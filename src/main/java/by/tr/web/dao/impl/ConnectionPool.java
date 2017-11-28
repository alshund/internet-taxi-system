package by.tr.web.dao.impl;

import by.tr.web.dao.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> usedConnections;

    private String driverName;
    private String URL;
    private String user;
    private String password;
    private int poolSize;

    private static final ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {

        setStartParameters();
        try {
            initialize();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws ConnectionPoolException {

        try {
            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            usedConnections = new ArrayBlockingQueue<>(poolSize);

            for (int connectionIndex = 0; connectionIndex < poolSize; connectionIndex++) {

                Connection connection = DriverManager.getConnection(URL, user, password);
                connectionQueue.add(connection);
            }
        } catch (ClassNotFoundException e) {

            throw new ConnectionPoolException("Connection driver class not found", e);
        } catch (SQLException e) {

            throw new ConnectionPoolException("Driver manager get connection exception", e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException {

        try {
            Connection connection = connectionQueue.take();
            usedConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            throw new ConnectionPoolException("Thread is interrupted before or during the activity", e);
        }
    }

    public void closeConnection(Connection connection) {


    }

    private void setStartParameters() {

        ResourceBundle bundle = ResourceBundle.getBundle(DBBundleKeys.BUNDLE_NAME);
        driverName = bundle.getString(DBBundleKeys.DRIVER_NAME);
        URL = bundle.getString(DBBundleKeys.URL);
        user = bundle.getString(DBBundleKeys.USER);
        password = bundle.getString(DBBundleKeys.PASS);
        setPoolSize(bundle.getString(DBBundleKeys.POOL_SIZE));
    }

    private void setPoolSize(String poolSize) {

        try {

            this.poolSize = Integer.parseInt(poolSize);
        } catch (NumberFormatException e) {

            this.poolSize = 10;
        }
    }
}
