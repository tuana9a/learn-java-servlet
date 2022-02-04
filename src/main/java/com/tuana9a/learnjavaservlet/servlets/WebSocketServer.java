package com.tuana9a.learnjavaservlet.servlets;

import com.tuana9a.learnjavaservlet.models.ChatMessage;
import com.tuana9a.learnjavaservlet.services.ChatService;
import com.tuana9a.learnjavaservlet.utils.JsonUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session) {
        ChatService service = ChatService.getInstance();
        String sessionId = session.getId();
        service.addSession(session);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(sessionId);
        service.sendMessage(sessionId, JsonUtils.getInstance().toJson(chatMessage));
    }

    @OnClose
    public void onClose(Session session) {
        ChatService service = ChatService.getInstance();
        service.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
        ChatService service = ChatService.getInstance();
        service.removeSession(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        JsonUtils jsonUtils = JsonUtils.getInstance();
        ChatService service = ChatService.getInstance();
        try {
            ChatMessage chatMessage = jsonUtils.fromJson(message, ChatMessage.class);
            String toId = chatMessage.getToId();
            service.sendMessage(toId, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
