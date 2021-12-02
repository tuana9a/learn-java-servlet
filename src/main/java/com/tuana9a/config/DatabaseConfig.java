package com.tuana9a.config;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class DatabaseConfig {
    private String url;
    private String username;
    private String password;
}
