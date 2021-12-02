package com.tuana9a.dao;

public class BookDao {
    private static final BookDao instance = new BookDao();

    public static BookDao getInstance() {
        return instance;
    }

    private void SimpleModelDao() {

    }

    public String welcome(String username) {
        return "You are nice: " + username;
    }
}
