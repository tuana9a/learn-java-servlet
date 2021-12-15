package com.tuana9a.context;

import com.tuana9a.config.AppConfig;
import com.tuana9a.config.DatabaseConfig;
import com.tuana9a.database.DatabaseClient;
import com.tuana9a.hibernate.HBDatabaseClient;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            System.out.println("=== " + this.getClass().getName() + " ===");
            ServletContext context = event.getServletContext();
            String driver = context.getInitParameter("driver");
            System.out.println("driver=" + driver);

            AppConfig.getInstance().load();
            DatabaseConfig databaseConfig = new DatabaseConfig();
            AppConfig config = AppConfig.getInstance();
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
