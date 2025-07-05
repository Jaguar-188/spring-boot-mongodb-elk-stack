package com.example.mdbspringboot.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "studentinfo")
public class StudentInfo {

    private String infoId;
    @NotNull
    private String studentId;
    @NotNull
    private String course;
    @NotNull
    private String percentage;

}
