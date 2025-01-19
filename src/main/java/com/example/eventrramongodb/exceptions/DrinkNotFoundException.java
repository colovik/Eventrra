package com.example.eventrramongodb.exceptions;

public class DrinkNotFoundException extends RuntimeException {
    public DrinkNotFoundException(String s) {
        super(s);
    }
}
