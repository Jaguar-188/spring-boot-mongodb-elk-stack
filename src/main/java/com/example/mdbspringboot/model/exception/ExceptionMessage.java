package com.example.mdbspringboot.model.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exception {

    private LocalDateTime timeStamp;
    private String message;
    private String details;
    private String statusLine;
    private int statusCode;

}
