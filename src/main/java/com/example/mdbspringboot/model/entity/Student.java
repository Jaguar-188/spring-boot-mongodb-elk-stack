package com.example.mdbspringboot.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("student")
public class Student {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String className;
    @NotNull
    private String stream;
    @NotNull
    private String hobby;
    @NotNull
    private String has_girlfriend;
}
