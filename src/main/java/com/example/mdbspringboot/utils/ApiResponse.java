package com.example.mdbspringboot.utils;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse<T> {

    private T studentDetails;
    private String statusCode;
    private String details;
    private String message;
}
