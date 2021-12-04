package com.tuana9a.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String name;
    private String publisher;
}
