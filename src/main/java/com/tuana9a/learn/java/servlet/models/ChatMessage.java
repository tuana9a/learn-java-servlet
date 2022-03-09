package com.tuana9a.learn.java.servlet.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String fromId;
    private String toId;
    private String message;
}
