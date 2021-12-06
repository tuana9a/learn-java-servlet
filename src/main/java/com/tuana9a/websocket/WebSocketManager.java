package com.tuana9a.websocket;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Hashtable;

public class WebSocketManager {
    private static final WebSocketManager instance = new WebSocketManager();
    private final Hashtable<String, Session> sessions = new Hashtable<>();

    private WebSocketManager() {

    }

    public static WebSocketManager getInstance() {
        return instance;
    }

    public void putSession(Session session) {
        if (session == null) return;
        sessions.put(session.getId(), session);
    }

    public void removeSession(Session session) {
        if (session == null) return;
        sessions.remove(session.getId());
    }

    public void sendMessage(String sessionId, String message) {
        try {
            sessions.get(sessionId).getBasicRemote().sendText(message);
        } catch (IOException ignored) {
        }
    }

    public void broadcastMessage(String message) {
        sessions.forEach((sessionId, session) -> sendMessage(sessionId, message));
    }

}
