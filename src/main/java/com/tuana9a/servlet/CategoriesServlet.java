package com.tuana9a.servlet;

import com.google.gson.Gson;
import com.tuana9a.dao.CategoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(value = "/api/public/categories")
public class CategoriesServlet extends HttpServlet {
    private CategoryDAO categoryDAO = new CategoryDAO();

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            writer.println(gson.toJson(categoryDAO.findAll()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(500);
            writer.println("error: " + throwables);
        }
    }
}
