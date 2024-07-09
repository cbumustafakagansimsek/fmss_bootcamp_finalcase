package com.patika.userservice.exception;


public class ExistUserException extends RuntimeException {
    public ExistUserException(String message) {
        super(message);
    }
}
