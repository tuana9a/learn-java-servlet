package com.tuana9a.learn.java.servlet.background;

import com.tuana9a.learn.java.servlet.repository.WebSocketRepository;

public class SyncClock implements Runnable {
    public SyncClock() {

    }

    @Override
    public void run() {
        while (true) {
            WebSocketRepository repository = WebSocketRepository.getInstance();
            repository.getSessionIds().forEach(sessionId -> repository.sendMessage(sessionId, String.valueOf(System.currentTimeMillis())));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
