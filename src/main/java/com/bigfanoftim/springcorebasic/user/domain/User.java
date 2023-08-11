package com.bigfanoftim.springcorebasic.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    private Long id;
    private String name;
    private Grade grade;

    @Builder
    public User(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}
