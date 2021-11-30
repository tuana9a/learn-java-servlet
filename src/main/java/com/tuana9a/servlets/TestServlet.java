package com.tuana9a.servlets;

import com.tuana9a.dao.SimpleUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        SimpleUserDao dao = SimpleUserDao.getInstance();

        req.setAttribute("welcome", dao.welcome(username));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/test/welcome.jsp");
        requestDispatcher.forward(req, resp);
    }
}
