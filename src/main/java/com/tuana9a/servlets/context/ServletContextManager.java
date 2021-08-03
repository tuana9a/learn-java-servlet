package com.tuana9a.servlets.context;

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

    public File getRealFile(String servletPath) {
        return new File(context.getRealPath(servletPath));
    }

    public String getRealPath(String servletPath) {
        return context.getRealPath(servletPath);
    }

}
