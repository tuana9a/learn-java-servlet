package com.tuana9a.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/jsp")
public class JspServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("this is GET request and YOU are success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("welcome", "Welcome " + username + " !");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/welcome.jsp");
        requestDispatcher.forward(req, resp);
    }
}
