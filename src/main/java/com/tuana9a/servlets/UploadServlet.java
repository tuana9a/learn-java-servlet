package com.tuana9a.servlets;

import com.tuana9a.models.ResponseEntity;
import com.tuana9a.servlets.context.ServletContextManager;
import com.tuana9a.utils.IoUtils;
import com.tuana9a.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(value = "/api/upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");

        if (filePart == null) {
            return;
        }

        String uploadPath = ServletContextManager.getInstance().getRealPath(filePart.getSubmittedFileName());

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = filePart.getInputStream();
            outputStream = new FileOutputStream(uploadPath);
            IoUtils.getInstance().writeInputToOutput(inputStream, outputStream);

        } catch (Exception ignored) {

        } finally {
            IoUtils.getInstance().close(inputStream);
            IoUtils.getInstance().close(outputStream);
        }

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance().toJson(ResponseEntity.builder()
                .code(1)
                .message("upload webapp.zip success")
                .build()));

    }

}
