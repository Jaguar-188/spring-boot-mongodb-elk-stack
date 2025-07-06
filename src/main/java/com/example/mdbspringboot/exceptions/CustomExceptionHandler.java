package com.example.mdbspringboot.exceptions;

import com.example.mdbspringboot.config.Logging;
import com.example.mdbspringboot.model.exception.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logging log = Logging.getLog();

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ExceptionMessage> UserNotFoundExceptionHandler(UserNotFoundException ex, WebRequest webRequest){

        log.info("Resolving User Not Found Exception");

        ExceptionMessage exception = new ExceptionMessage(LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false).replace("uri=",""),
                String.valueOf(HttpStatus.NOT_FOUND),
                Integer.parseInt(String.valueOf(HttpStatus.NOT_FOUND).substring(0,3)));

        return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ExceptionMessage> BadRequestExceptionHandler(BadRequestException ex, WebRequest webRequest){

        log.info("Resolving Bad Request Exception");

        ExceptionMessage exception = new ExceptionMessage(LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false).replace("uri=",""),
                String.valueOf(HttpStatus.BAD_REQUEST),
                Integer.parseInt(String.valueOf(HttpStatus.BAD_REQUEST).substring(0,3)));

        return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TypeMisMatchException.class})
    protected ResponseEntity<ExceptionMessage> TypeMisMatchExceptionHandler(TypeMisMatchException ex, WebRequest webRequest){

        log.info("Resolving Type MisMatch Exception");

        ExceptionMessage exception = new ExceptionMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false).replace("uri=",""),
                String.valueOf(HttpStatus.BAD_REQUEST),
                Integer.parseInt(String.valueOf(HttpStatus.BAD_REQUEST).substring(0,3))
        );

        return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InsufficientAmountException.class})
    protected ResponseEntity<ExceptionMessage> insufficientAmountExceptionHandler(InsufficientAmountException insufficientAmountException, WebRequest webRequest){

        log.info("Resolving Insufficient Amount Exception");

        ExceptionMessage exception = new ExceptionMessage(
                LocalDateTime.now(),
                insufficientAmountException.getMessage(),
                webRequest.getDescription(false).replace("uri=",""),
                String.valueOf(HttpStatus.NOT_ACCEPTABLE),
                Integer.parseInt(String.valueOf(HttpStatus.NOT_ACCEPTABLE).substring(0,3))
        );

        return new ResponseEntity<>(exception,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({AmountCanNotBeNegativeOrZero.class})
    protected ResponseEntity<ExceptionMessage> amountCanNotBeNegativeOrZero(AmountCanNotBeNegativeOrZero amountCanNotBeNegativeOrZero, WebRequest webRequest){

        log.info("Resolving Amount can not be Zero or Negative Exception");

        ExceptionMessage exception = new ExceptionMessage(
                LocalDateTime.now(),
                amountCanNotBeNegativeOrZero.getMessage(),
                webRequest.getDescription(false).replace("uri=",""),
                String.valueOf(HttpStatus.BAD_REQUEST),
                Integer.parseInt(String.valueOf(HttpStatus.BAD_REQUEST).substring(0,3))
        );

        return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
    }
}
