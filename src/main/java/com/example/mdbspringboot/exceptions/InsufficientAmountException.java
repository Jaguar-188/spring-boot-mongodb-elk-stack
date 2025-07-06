package com.example.mdbspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InsufficientAmountException extends RuntimeException {

    public InsufficientAmountException(String message){
        super(message);
    }
}
