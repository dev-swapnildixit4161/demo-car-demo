package com.nashtech.exception;

public class MyCustomException extends RuntimeException {

    public MyCustomException(String message) {
        super(message);
    }

    public MyCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}