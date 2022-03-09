package com.tuana9a.learn.java.servlet.config;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseConfig {
    private String url;
    private String username;
    private String password;
}
