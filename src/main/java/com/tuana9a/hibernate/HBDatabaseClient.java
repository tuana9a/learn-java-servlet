package com.tuana9a.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HBDatabaseClient {
    private static final HBDatabaseClient instance = new HBDatabaseClient();

    private SessionFactory sessionFactory;

    private HBDatabaseClient() {

    }

    public static HBDatabaseClient getInstance() {
        return instance;
    }

    public SessionFactory createSessionFactory(List<String> mapResources) {
        Configuration cfg = new Configuration();
        mapResources.forEach(cfg::addResource);
        sessionFactory = cfg.configure().buildSessionFactory();
        return sessionFactory;
    }

    public SessionFactory createSessionFactory() {
        Configuration cfg = new Configuration();
        sessionFactory = cfg.configure().buildSessionFactory();
        return sessionFactory;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
