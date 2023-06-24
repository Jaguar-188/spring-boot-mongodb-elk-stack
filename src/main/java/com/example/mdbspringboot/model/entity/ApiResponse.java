package com.example.mdbspringboot.model.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse {

    private List<Student> studentDetails;
    private String statusCode;
    private String details;
    private String message;
}
