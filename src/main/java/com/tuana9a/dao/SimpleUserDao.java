package com.tuana9a.dao;

public class SimpleUserDao {
    private static final SimpleUserDao instance = new SimpleUserDao();

    public static SimpleUserDao getInstance() {
        return instance;
    }

    private void SimpleModelDao() {

    }

    public String welcome(String username) {
        return "You are nice: " + username;
    }
}
