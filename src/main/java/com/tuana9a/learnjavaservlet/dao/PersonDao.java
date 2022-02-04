package com.tuana9a.learnjavaservlet.dao;

import com.tuana9a.learnjavaservlet.models.Person;

public class PersonDao extends BaseDao<Person> {
    private static final PersonDao instance = new PersonDao();

    private PersonDao() {
        super("person", Person.class);
    }

    public static PersonDao getInstance() {
        return instance;
    }
}
