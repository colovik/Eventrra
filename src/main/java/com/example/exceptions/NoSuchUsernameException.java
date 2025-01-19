package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchUsernameException extends RuntimeException{

    public NoSuchUsernameException() {
        super("No such username Exception");
    }

}