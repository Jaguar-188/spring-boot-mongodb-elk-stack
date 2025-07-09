package com.example.mdbspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectAccountNumberException extends RuntimeException{

    public IncorrectAccountNumberException(String message) {
        super(message);
    }

    public IncorrectAccountNumberException(){
        super();
    }
}
