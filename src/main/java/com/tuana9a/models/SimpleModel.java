package com.tuana9a.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleModel {
    private Integer id;
    private String name;
    private Integer age;
}
