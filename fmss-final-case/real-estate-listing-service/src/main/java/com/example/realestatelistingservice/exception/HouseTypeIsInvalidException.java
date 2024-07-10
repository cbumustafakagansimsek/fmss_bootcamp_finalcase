package com.example.realestatelistingservice.exception;

public class HouseTypeIsInvalidException extends RuntimeException{
    public HouseTypeIsInvalidException(String message) {
        super(message);
    }
}
