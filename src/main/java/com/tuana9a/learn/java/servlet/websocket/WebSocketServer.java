package com.tuana9a.learn.java.servlet.websocket;

import com.tuana9a.learn.java.servlet.repository.WebSocketRepository;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session) {
        WebSocketRepository service = WebSocketRepository.getInstance();
        service.addSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        WebSocketRepository service = WebSocketRepository.getInstance();
        service.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
        WebSocketRepository service = WebSocketRepository.getInstance();
        service.removeSession(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        // do nothing
    }

}
