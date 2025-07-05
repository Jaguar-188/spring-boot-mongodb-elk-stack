package com.example.mdbspringboot.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class StudentDTO {

    private String id;
    private String name;
    private String className;
    private String stream;
    private String hobby;
    private String has_girlfriend;
    private String percentage;
}
