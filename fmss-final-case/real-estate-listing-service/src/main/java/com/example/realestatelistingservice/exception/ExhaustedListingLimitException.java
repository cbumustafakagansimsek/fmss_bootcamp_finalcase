package com.example.realestatelistingservice.exception;

public class ExhaustedListingLimitException extends RuntimeException{
    public ExhaustedListingLimitException(String message) {
        super(message);
    }
}
