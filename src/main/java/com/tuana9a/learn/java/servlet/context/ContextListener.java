package com.tuana9a.learn.java.servlet.context;

import com.tuana9a.learn.java.servlet.database.JdbcMySQLClient;
import com.tuana9a.learn.java.servlet.configs.AppConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println(this.getClass().getName() + " is loading servlet context");
        AppConfig config = AppConfig.getInstance();
        config.load();
        Executor executor = Executors.newFixedThreadPool(8);
        executor.execute(() -> {
            JdbcMySQLClient mySQLClient = JdbcMySQLClient.getInstance();
            mySQLClient.createConnection(config.DATABASE_URL(), config.DATABASE_USERNAME(), config.DATABASE_PASSWORD());
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
