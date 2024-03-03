package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchIDException extends RuntimeException{
    public NoSuchIDException(Integer id) {
        super(String.format("There is no user with id %d", id));
    }
}
