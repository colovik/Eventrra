package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidUsernameOrPasswordException extends RuntimeException {

    public InvalidUsernameOrPasswordException(){
        super("Invalid username or password");
    }
}
