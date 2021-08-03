package com.tuana9a.security;

import com.tuana9a.config.AppConfig;

public class AdminService {
    private static final AdminService instance = new AdminService();

    private AdminService() {

    }

    public static AdminService getInstance() {
        return instance;
    }

    public boolean isAdmin(String username) {
        return AppConfig.getInstance().SECRETS.contains(username);
    }

}
