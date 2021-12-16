package com.tuana9a.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JspServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("if you see this text then your GET request is success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("welcome", "Welcome " + username + " !");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/welcome.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("=== " + this.getClass().getName() + " ===");
        String name = config.getInitParameter("name");
        String girl = config.getInitParameter("girl");
        System.out.println("name=" + name);
        System.out.println("girl=" + girl);
    }
}
