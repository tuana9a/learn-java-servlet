package com.tuana9a.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/product";
    private static final String userName = "root";
    private static final String password = "Tuantai123";

    public static boolean connect() {
        connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection != null;
    }

    public static boolean disconnect() {
        if (connection != null) {
            try {
                connection.close();
                return connection.isClosed();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
