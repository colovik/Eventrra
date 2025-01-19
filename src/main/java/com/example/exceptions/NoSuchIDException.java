package com.example.exceptions;

public class NoSuchIDException extends RuntimeException {
    public NoSuchIDException(String id) {
        super("No entity found with ID: " + id);
    }
}

