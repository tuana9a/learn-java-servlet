package com.tuana9a.learn.java.servlet.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.List;

import com.tuana9a.learn.java.servlet.config.AppConfig;

public class HBMySQLClient {
    private static final HBMySQLClient instance = new HBMySQLClient();

    private SessionFactory sessionFactory;

    private HBMySQLClient() {

    }

    public static HBMySQLClient getInstance() {
        return instance;
    }

    public SessionFactory createSessionFactory(List<String> mapResources) {
        Configuration cfg = new Configuration();
        mapResources.forEach(cfg::addResource);
        sessionFactory = cfg.configure().buildSessionFactory();
        return sessionFactory;
    }

    public SessionFactory createSessionFactory() {
        AppConfig config = AppConfig.getInstance();
        Configuration cfg = new Configuration();
        cfg.addResource(config.properties.getProperty("hibernate.mappers.Book"));
        sessionFactory = cfg.configure(new File(config.HIBERNATE_CONFIG_PATH())).buildSessionFactory();
        return sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
