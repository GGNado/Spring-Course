package com.giggi.firstproject.exception.general;

public class IdNotValidException extends RuntimeException{
    public IdNotValidException(String message) {
        super(message);
    }

    public IdNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotValidException(Throwable cause) {
        super(cause);
    }
}
