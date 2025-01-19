package com.example.exceptions;

public class PhotographerNotFoundException extends RuntimeException {
    public PhotographerNotFoundException(String message, String bandId) {
        super(String.format(message, bandId));
    }
}
