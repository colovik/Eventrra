package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchAddressException extends RuntimeException{

    public NoSuchAddressException(String address) {
        super(String.format("Address %s doesn't exists", address));
    }

}