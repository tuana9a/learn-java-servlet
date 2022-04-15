package com.tuana9a.learn.java.servlet.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tuana9a.learn.java.servlet.configs.AppConfig;

import java.util.Date;

public class JwtUtils {

    private static final JwtUtils instance = new JwtUtils();

    private JwtUtils() {

    }

    public static JwtUtils getInstance() {
        return instance;
    }

    public String createToken(String username) {
        AppConfig config = AppConfig.getInstance();
        Date expire = new Date(System.currentTimeMillis() + Long.parseLong(config.SECURITY_JWT_EXPIRE()));
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expire)
                .sign(Algorithm.HMAC512(config.SECURITY_JWT_SECRET()));
    }

    public String decodeToken(String token) {
        if (token == null) {
            return null;
        }
        try {
            AppConfig config = AppConfig.getInstance();
            return JWT.require(Algorithm.HMAC512(config.SECURITY_JWT_SECRET()))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception ignored) {
            return null;
        }
    }

}
