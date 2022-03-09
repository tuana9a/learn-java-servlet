package com.tuana9a.learn.java.servlet.servlets;

import com.tuana9a.learn.java.servlet.models.ResponseEntity;
import com.tuana9a.learn.java.servlet.utils.IoUtils;
import com.tuana9a.learn.java.servlet.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(value = "/upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        IoUtils ioUtils = IoUtils.getInstance();

        if (filePart == null) {
            return;
        }

        String uploadPath = this.getServletContext().getRealPath(filePart.getSubmittedFileName());

        InputStream inputStream = null;
        OutputStream outputStream = null;

        // In production should try/catch this io block code
        try {
            inputStream = filePart.getInputStream();
            outputStream = new FileOutputStream(uploadPath);
            ioUtils.writeInputToOutput(inputStream, outputStream);
        } finally {
            ioUtils.close(inputStream);
            ioUtils.close(outputStream);
        }

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(JsonUtils.getInstance()
                .toJson(ResponseEntity.builder()
                        .code(1)
                        .message("upload webapp.zip success")
                        .build()));

    }

}
