package com.tuana9a.learnjavaservlet.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String fromId;
    private String toId;
    private String message;
}
