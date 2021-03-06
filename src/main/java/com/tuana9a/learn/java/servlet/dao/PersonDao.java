package com.tuana9a.learn.java.servlet.dao;

import com.tuana9a.learn.java.servlet.models.Person;

public class PersonDao extends BaseDao<Person> {
    private static final PersonDao instance = new PersonDao();

    private PersonDao() {
        super("person", Person.class);
    }

    public static PersonDao getInstance() {
        return instance;
    }
}
