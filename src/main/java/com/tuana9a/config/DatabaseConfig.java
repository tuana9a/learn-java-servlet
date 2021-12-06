package com.tuana9a.config;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class DatabaseConfig {
    private String url;
    private String username;
    private String password;
}