package com.tuana9a.servlets.context;

import com.tuana9a.config.AppConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        AppConfig.getInstance().load();
        ServletContextManager.getInstance().setContext(event.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
