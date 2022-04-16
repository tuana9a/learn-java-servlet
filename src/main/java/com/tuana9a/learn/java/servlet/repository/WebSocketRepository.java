package com.tuana9a.learn.java.servlet.repository;

import com.tuana9a.learn.java.servlet.utils.IoUtils;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

public class WebSocketRepository {
    private static final WebSocketRepository instance = new WebSocketRepository();
    private final Hashtable<String, Session> sessions = new Hashtable<>();

    private WebSocketRepository() {

    }

    public static WebSocketRepository getInstance() {
        return instance;
    }

    public void addSession(Session session) {
        if (session == null) return;
        sessions.put(session.getId(), session);
    }

    public void removeSession(Session session) {
        if (session == null) return;
        sessions.remove(session.getId());
        IoUtils.getInstance().close(session);
    }

    public Set<String> getSessionIds() {
        return sessions.keySet();
    }

    public void sendMessage(String sessionId, String message) {
        if (sessionId == null) return;
        Session session = sessions.get(sessionId);
        if (session == null) return;
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
