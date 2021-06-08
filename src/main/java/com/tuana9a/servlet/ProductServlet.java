package com.tuana9a.servlet;

import com.google.gson.Gson;
import com.tuana9a.dao.ProductDAO;
import com.tuana9a.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(value = "/api/public/Product")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productDAO.findById(id);
            if (product != null)
                writer.println(gson.toJson(product));
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
            Product product = gson.fromJson(req.getReader(), Product.class);
            product.setDeleted(false);
            product.setCreateDate( new Date());
            Product saved = productDAO.insert(product);
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
            Product product = gson.fromJson(req.getReader(), Product.class);
            if (productDAO.update(product))
                writer.println(gson.toJson(product));
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
            if (productDAO.delete(id))
                writer.println("deleted");
            else  writer.println("id is not exist");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(500);
            writer.println("error: " + throwables);
        }
    }
}
