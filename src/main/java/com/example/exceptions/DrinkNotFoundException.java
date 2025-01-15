package com.example.exceptions;

public class DrinkNotFoundException extends RuntimeException {
    public DrinkNotFoundException(String s) {
        super("Drink Not Found Exception");
    }
}
