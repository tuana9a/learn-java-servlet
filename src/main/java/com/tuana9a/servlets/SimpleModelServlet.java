package com.tuana9a.servlets;

import com.tuana9a.dao.SimpleModelDao;
import com.tuana9a.models.ResponseEntity;
import com.tuana9a.models.SimpleModel;
import com.tuana9a.utils.JsonUtils;
import com.tuana9a.utils.LogUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/api/simple")
public class SimpleModelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        SimpleModel result = null;
        try {
            result = SimpleModelDao.getInstance().findById(id);
        } catch (SQLException e) {
            LogUtils.getInstance().LOGGER.error(this.getClass().getName(), e);
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
        SimpleModel model = JsonUtils.getInstance().fromJson(req.getReader(), SimpleModel.class);
        SimpleModel result = null;
        try {
            result = SimpleModelDao.getInstance().insert(model);
        } catch (SQLException e) {
            LogUtils.getInstance().LOGGER.error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("post success")
                .data(result)
                .build()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleModel model = JsonUtils.getInstance().fromJson(req.getReader(), SimpleModel.class);
        Boolean result = null;
        try {
            result = SimpleModelDao.getInstance().update(model);
        } catch (SQLException e) {
            LogUtils.getInstance().LOGGER.error(this.getClass().getName(), e);
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
            result = SimpleModelDao.getInstance().delete(id);
        } catch (SQLException e) {
            LogUtils.getInstance().LOGGER.error(this.getClass().getName(), e);
        }
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("delete success")
                .data(result)
                .build()));
    }
}
