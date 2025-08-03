package com.ra.javaserviecproject.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoHandlerFound(NoHandlerFoundException ex) {
        return new ResponseEntity<>("Endpoint not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccess(ResourceAccessException ex) {
        return new ResponseEntity<>("Failed to access external resource", HttpStatus.SERVICE_UNAVAILABLE);
    }
}