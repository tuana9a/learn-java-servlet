package com.tuana9a.servlets;

import com.tuana9a.dao.PersonDao;
import com.tuana9a.models.ResponseEntity;
import com.tuana9a.models.Person;
import com.tuana9a.utils.JsonUtils;
import com.tuana9a.utils.LogUtils;

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
        long id = Long.parseLong(req.getParameter("id"));
        Person result = null;
        try {
            result = PersonDao.getInstance().findById(id);
        } catch (Exception e) {
            LogUtils.getLogger().error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("get success")
                .data(result)
                .build()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = JsonUtils.getInstance().fromJson(req.getReader(), Person.class);
        boolean success = false;
        try {
            success = PersonDao.getInstance().insert(person);
        } catch (Exception e) {
            LogUtils.getLogger().error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("post success")
                .data(success)
                .build()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person model = JsonUtils.getInstance().fromJson(req.getReader(), Person.class);
        Boolean result = null;
        try {
            result = PersonDao.getInstance().update(model);
        } catch (Exception e) {
            LogUtils.getLogger().error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("put success")
                .data(result)
                .build()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Boolean result = null;
        try {
            result = PersonDao.getInstance().delete(id);
        } catch (SQLException e) {
            LogUtils.getLogger().error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("delete success")
                .data(result)
                .build()));
    }
}
