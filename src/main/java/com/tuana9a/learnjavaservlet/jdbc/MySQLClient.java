package com.tuana9a.learnjavaservlet.jdbc;

import com.tuana9a.learnjavaservlet.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLClient {
    private Connection connection;

    private static final MySQLClient instance = new MySQLClient();

    private MySQLClient() {

    }

    public static MySQLClient getInstance() {
        return instance;
    }

    public Connection createConnection(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection createConnection(DatabaseConfig option) {
        return this.createConnection(option.getUrl(), option.getUsername(), option.getPassword());
    }

    public Connection getConnection() {
        return connection;
    }

}
