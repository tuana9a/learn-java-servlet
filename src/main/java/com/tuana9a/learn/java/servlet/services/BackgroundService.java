package com.tuana9a.learn.java.servlet.services;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BackgroundService {
    private static  final BackgroundService instance = new BackgroundService();
    private final Executor executor;

    private BackgroundService() {
        executor = Executors.newFixedThreadPool(4);
    }

    public static BackgroundService getInstance() {
        return instance;
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
