package com.tuana9a.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ResponseEntity {
    private Integer code;
    private String message;
    private Object data;
}
