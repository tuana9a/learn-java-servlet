package com.tuana9a.learnjavaservlet.context;

import com.tuana9a.learnjavaservlet.config.AppConfig;
import com.tuana9a.learnjavaservlet.config.DatabaseConfig;
import com.tuana9a.learnjavaservlet.jdbc.MySQLClient;
import com.tuana9a.learnjavaservlet.hibernate.HBMySQLClient;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println(this.getClass().getName());
        ServletContext context = event.getServletContext();
        String driver = context.getInitParameter("driver");
        System.out.println("driver=" + driver);

        AppConfig config = AppConfig.getInstance();
        config.load();

        Executor executor = Executors.newFixedThreadPool(8);
        executor.execute(() -> HBMySQLClient.getInstance().createSessionFactory());
        executor.execute(() -> {
            DatabaseConfig databaseConfig = new DatabaseConfig();
            databaseConfig.setUrl(config.DATABASE_URL());
            databaseConfig.setUsername(config.DATABASE_USERNAME());
            databaseConfig.setPassword(config.DATABASE_PASSWORD());

            MySQLClient mySQLClient = MySQLClient.getInstance();
            mySQLClient.createConnection(databaseConfig);
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
