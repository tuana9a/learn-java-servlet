package com.tuana9a.learn.java.servlet.context;

import com.tuana9a.learn.java.servlet.background.SyncClock;
import com.tuana9a.learn.java.servlet.database.JdbcMySQLClient;
import com.tuana9a.learn.java.servlet.configs.AppConfig;
import com.tuana9a.learn.java.servlet.services.BackgroundService;

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
        BackgroundService backgroundService = BackgroundService.getInstance();
        JdbcMySQLClient mySQLClient = JdbcMySQLClient.getInstance();
        backgroundService.execute(() -> mySQLClient.createConnection(config.DATABASE_URL(), config.DATABASE_USERNAME(), config.DATABASE_PASSWORD()));
        backgroundService.execute(new SyncClock());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
