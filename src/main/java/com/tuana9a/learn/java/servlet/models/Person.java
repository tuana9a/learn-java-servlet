package com.tuana9a.learn.java.servlet.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private Boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) && Objects.equals(name, person.name) && Objects.equals(age, person.age) && Objects.equals(deleted, person.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, deleted);
    }
}
