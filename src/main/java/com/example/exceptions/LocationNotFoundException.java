package com.example.exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String s) {
        super(s);
    }
}
