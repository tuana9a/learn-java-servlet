package com.tuana9a.websocket;

import com.google.gson.JsonObject;
import com.tuana9a.utils.IoUtils;
import com.tuana9a.utils.JsonUtils;
import com.tuana9a.utils.LogUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session) {
        WebSocketManager.getInstance().putSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        WebSocketManager.getInstance().removeSession(session);
        IoUtils.getInstance().close(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LogUtils.getLogger().error("WebSocketError: SessionId: " + session.getId(), error);
        WebSocketManager.getInstance().removeSession(session);
        IoUtils.getInstance().close(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            JsonObject object = JsonUtils.getInstance().fromJson(message, JsonObject.class);
            System.out.println(object);
        } catch (Exception ignored) {
        }
    }

}
