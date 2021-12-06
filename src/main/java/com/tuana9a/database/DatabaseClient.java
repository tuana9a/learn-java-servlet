package com.tuana9a.database;

import com.tuana9a.config.DatabaseConfig;

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

    public Connection createConnection(DatabaseConfig option) throws SQLException {
        return this.createConnection(option.getUrl(), option.getUsername(), option.getPassword());
    }

    public Connection getConnection() {
        return connection;
    }

}
