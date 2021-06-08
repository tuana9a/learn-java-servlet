package com.tuana9a.servlet;

import com.google.gson.Gson;
import com.tuana9a.dao.CategoryDAO;
import com.tuana9a.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(value = "/api/public/Category")
public class CategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO = new CategoryDAO();

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryDAO.findById(id);
            if (category != null)
                writer.println(gson.toJson(category));
            else  writer.println("id is not exist");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(500);
            writer.println("error: " + throwables);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            Category category = gson.fromJson(req.getReader(), Category.class);
            category.setDeleted(false);
            Category saved = categoryDAO.insert(category);
            if (saved != null)
                writer.println(gson.toJson(saved));
            else {
                resp.setStatus(400);
                writer.println("data is invalid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(500);
            writer.println("error: " + throwables);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            Category category = gson.fromJson(req.getReader(), Category.class);
            if (categoryDAO.update(category))
                writer.println(gson.toJson(category));
            else {
                resp.setStatus(400);
                writer.println("data is invalid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(500);
            writer.println("error: " + throwables);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            if (categoryDAO.delete(id))
                writer.println("deleted");
            else  writer.println("id is not exist");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(500);
            writer.println("error: " + throwables);
        }
    }
}
