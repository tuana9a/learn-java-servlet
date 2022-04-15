package com.tuana9a.learn.java.servlet.security;

import com.tuana9a.learn.java.servlet.configs.AppConfig;
import com.tuana9a.learn.java.servlet.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/api/admin/*"})
public class CookieJwtTokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("Token")) {
                    String jwtToken = cookie.getValue();
                    String secret = JwtUtils.getInstance().decodeToken(jwtToken);
                    if (AppConfig.getInstance().SECRETS.contains(secret)) {
                        chain.doFilter(request, response);
                        return;
                    }
                    break;
                }
            }
        }
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().print(AppConfig.getInstance().SECURITY_REJECT_MESSAGE());
    }

    @Override
    public void destroy() {
    }

}
