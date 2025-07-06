package com.example.mdbspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AmountCanNotBeNegativeOrZero extends RuntimeException{

    public AmountCanNotBeNegativeOrZero(String message) {
        super(message);
    }
}
