package com.tuana9a.learnjavaservlet.servlets;

import com.tuana9a.learnjavaservlet.dao.PersonDao;
import com.tuana9a.learnjavaservlet.models.ResponseEntity;
import com.tuana9a.learnjavaservlet.models.Person;
import com.tuana9a.learnjavaservlet.utils.JsonUtils;
import com.tuana9a.learnjavaservlet.utils.LogUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/api/person")
public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object result = null;
        try {
            String id = req.getParameter("id");
            if (id == null) {
                result = PersonDao.getInstance().findAll();
            } else {
                result = PersonDao.getInstance().findById(Long.parseLong(id));
            }
        } catch (Exception e) {
            LogUtils.getLogger().error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("get")
                .data(result)
                .build()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean success = false;
        try {
            Person person = JsonUtils.getInstance().fromJson(req.getReader(), Person.class);
            success = PersonDao.getInstance().insert(person);
        } catch (Exception e) {
            LogUtils.getLogger().error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("post")
                .data(success)
                .build()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean result = null;
        try {
            Person model = JsonUtils.getInstance().fromJson(req.getReader(), Person.class);
            result = PersonDao.getInstance().update(model);
        } catch (Exception e) {
            LogUtils.getLogger().error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("put")
                .data(result)
                .build()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean result = null;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            result = PersonDao.getInstance().delete(id);
        } catch (SQLException e) {
            LogUtils.getLogger().error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("delete")
                .data(result)
                .build()));
    }
}
