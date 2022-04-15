package com.tuana9a.learn.java.servlet.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcMySQLClient {
    private Connection connection;

    private static final JdbcMySQLClient instance = new JdbcMySQLClient();

    private JdbcMySQLClient() {

    }

    public static JdbcMySQLClient getInstance() {
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

    public Connection getConnection() {
        return connection;
    }

}
