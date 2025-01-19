package com.example.exceptions;

public class CateringNotFoundException extends RuntimeException {
    public CateringNotFoundException(String message, String bandId) {
        super(String.format(message, bandId));
    }
}
