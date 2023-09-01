package com.storefront.exception;

import jakarta.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    ResponseEntity<Object> itemConroller(IOException ioException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while processing image file!");
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<Object> orderException(ValidationException validationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationException.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Object> illegalArgument(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(illegalArgumentException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> genericMessage(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error while processing request, maybe the required parameter is missing!");
    }
}