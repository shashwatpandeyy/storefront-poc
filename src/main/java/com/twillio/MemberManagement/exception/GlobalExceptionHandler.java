package com.twillio.MemberManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    ResponseEntity<Object> errorInCrudOperation(MemberException memberException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(memberException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> unknownException(MemberException memberException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(memberException.getMessage());
    }
}
