package com.example.mdbspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect Data Format")
public class TypeMisMatchException extends RuntimeException{

    public TypeMisMatchException(String message){
        super(message);
    }
}
