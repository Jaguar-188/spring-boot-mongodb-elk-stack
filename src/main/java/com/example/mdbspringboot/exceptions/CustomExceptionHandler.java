package com.example.mdbspringboot.exceptions;

import com.example.mdbspringboot.model.exception.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<Exception> UserNotFoundExceptionHandler(Exception ex, WebRequest webRequest){

        String message = "Provided name " +ex.getMessage()+ " is not present in collection. ";

        Exception exception = new Exception(LocalDateTime.now(),message,
                webRequest.getDescription(false),
                String.valueOf(HttpStatus.NOT_FOUND),
                Integer.parseInt(String.valueOf(HttpStatus.NOT_FOUND).substring(0,2)));

        return new ResponseEntity<Exception>(exception,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<Exception> BadRequestExceptionHandler(Exception ex, WebRequest webRequest){

        String message = "Bad Request";

        Exception exception = new Exception(LocalDateTime.now(),message,
                webRequest.getDescription(true),
                String.valueOf(HttpStatus.BAD_REQUEST),
                Integer.parseInt(String.valueOf(HttpStatus.BAD_REQUEST).substring(0,2)));

        return new ResponseEntity<Exception>(exception,HttpStatus.BAD_REQUEST);
    }
}
