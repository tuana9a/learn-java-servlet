package com.tuana9a.services;

import com.tuana9a.utils.IoUtils;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Hashtable;

public class ChatService {
    private static final ChatService instance = new ChatService();
    private final Hashtable<String, Session> sessions = new Hashtable<>();

    private ChatService() {

    }

    public static ChatService getInstance() {
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
