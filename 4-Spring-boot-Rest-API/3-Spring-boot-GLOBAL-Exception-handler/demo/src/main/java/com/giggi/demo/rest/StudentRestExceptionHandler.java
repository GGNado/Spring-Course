package com.giggi.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    // Gestisco l'eccezione StudentNotFoundException
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, org.springframework.http.HttpStatus.NOT_FOUND);
    }

    // Aggiungo un metodo per gestire un'eccezione generica
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage()); // cambia il messaggio di errore se ti serve
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
