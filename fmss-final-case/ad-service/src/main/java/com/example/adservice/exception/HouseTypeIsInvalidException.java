package com.example.adservice.exception;

public class HouseTypeIsInvalidException extends RuntimeException{
    public HouseTypeIsInvalidException(String message) {
        super(message);
    }
}
