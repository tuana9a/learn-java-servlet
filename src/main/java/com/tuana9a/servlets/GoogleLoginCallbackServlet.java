package com.tuana9a.servlets;

import com.google.gson.JsonObject;
import com.tuana9a.config.AppConfig;
import com.tuana9a.security.AdminService;
import com.tuana9a.utils.JwtUtils;
import com.tuana9a.services.GoogleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login/callback")
public class GoogleLoginCallbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = null;
        GoogleService googleService = GoogleService.getInstance();
        try {
            String code = req.getParameter("code");
            String accessToken = googleService.createAccessToken(code);
            JsonObject user = googleService.getUserInfo(accessToken);
            username = user.get("email").getAsString();
        } catch (Exception ignored) {
        }

        if (!AdminService.getInstance().isAdmin(username)) {
            resp.sendRedirect("/?login_success=false");
            return;
        }

        Cookie cookie = new Cookie("auth", JwtUtils.getInstance().createToken(username));
        cookie.setPath("/");
        cookie.setMaxAge(Integer.parseInt(AppConfig.getInstance().SECURITY_COOKIE_EXPIRE));

        resp.addCookie(cookie);
        resp.sendRedirect("/?login_success=true");
    }

}
