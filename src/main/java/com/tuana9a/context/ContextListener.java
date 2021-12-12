package com.tuana9a.context;

import com.tuana9a.config.AppConfig;
import com.tuana9a.config.DatabaseConfig;
import com.tuana9a.database.DatabaseClient;
import com.tuana9a.hibernate.HBDatabaseClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContextManager.getInstance().setContext(event.getServletContext());
            AppConfig config = AppConfig.getInstance();
            config.load();

            DatabaseConfig databaseConfig = new DatabaseConfig();
            databaseConfig.setUrl(config.DATABASE_URL());
            databaseConfig.setUsername(config.DATABASE_USERNAME());
            databaseConfig.setPassword(config.DATABASE_PASSWORD());

            DatabaseClient.getInstance().createConnection(databaseConfig);
            HBDatabaseClient.getInstance().createSessionFactory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
