package com.tuana9a.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseClient {
    private Connection connection;

    private static final DatabaseClient instance = new DatabaseClient();

    private DatabaseClient() {

    }

    public static DatabaseClient getInstance() {
        return instance;
    }

    public Connection createConnection(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
