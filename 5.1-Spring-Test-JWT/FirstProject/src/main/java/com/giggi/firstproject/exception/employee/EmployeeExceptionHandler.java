package com.giggi.firstproject.exception.employee;

import com.giggi.firstproject.exception.general.IdNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc) {
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(404);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(IdNotValidException exc) {
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(NumberFormatException exc) {
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(BAD_REQUEST.value());
        error.setMessage("Stai usando un id non valido");
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}
