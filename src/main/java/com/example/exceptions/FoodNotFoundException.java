package com.example.exceptions;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(String s) {
        super("Food Not Found Exception");
    }
}
