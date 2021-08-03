package com.tuana9a.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEntity {
    private Integer code;
    private String message;
    private Object data;
}
