package com.tuana9a.context;

import com.tuana9a.config.AppConfig;
import com.tuana9a.database.DatabaseClient;
import com.tuana9a.hibernate.HBDatabaseClient;
import com.tuana9a.config.DatabaseConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            AppConfig config = AppConfig.getInstance();
            List<String> mapResources = new LinkedList<>();
            mapResources.add("Book.hbm.xml");

            config.load();
            ServletContextManager.getInstance().setContext(event.getServletContext());
            DatabaseClient.getInstance()
                    .createConnection(DatabaseConfig.builder()
                            .url(config.DATABASE_URL)
                            .username(config.DATABASE_USERNAME)
                            .password(config.DATABASE_PASSWORD)
                            .build());
            HBDatabaseClient.getInstance().createSessionFactory(mapResources);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
