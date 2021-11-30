package com.tuana9a.context;

import javax.servlet.ServletContext;
import java.io.File;

public class ServletContextManager {
    private ServletContext context;
    private static final ServletContextManager instance = new ServletContextManager();

    private ServletContextManager() {

    }

    public static ServletContextManager getInstance() {
        return instance;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public ServletContext getContext() {
        return context;
    }
}
