package com.patika.gateway.exception;

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}
