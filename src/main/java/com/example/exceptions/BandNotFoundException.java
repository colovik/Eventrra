package com.example.exceptions;

public class BandNotFoundException extends RuntimeException {
    public BandNotFoundException(String message, String bandId) {
        super(String.format(message, bandId));
    }
}
