package com.example.adservice.exception;

public class ExhaustedAdLimitException extends RuntimeException{
    public ExhaustedAdLimitException(String message) {
        super(message);
    }
}
