package com.tuana9a.factory;

import com.tuana9a.utils.IoUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {
    public static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/product";
    private static final String userName = "root";
    private static final String password = "Tuantai123";

    private static final DatabaseConnectionFactory instance = new DatabaseConnectionFactory();

    private DatabaseConnectionFactory() {

    }

    public static DatabaseConnectionFactory getInstance() {
        return instance;
    }

    public Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
