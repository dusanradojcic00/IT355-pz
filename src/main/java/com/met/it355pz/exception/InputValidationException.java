package com.met.it355pz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InputValidationException extends RuntimeException {
    public InputValidationException(String s) {
        super(s);
    }
}
