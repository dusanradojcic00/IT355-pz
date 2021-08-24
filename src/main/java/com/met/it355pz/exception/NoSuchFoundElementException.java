package com.met.it355pz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchFoundElementException extends RuntimeException {

    public NoSuchFoundElementException(String element) {
        super("Entity not found: " + element);
    }

    public NoSuchFoundElementException(Long id) {
        super("Entitiy with ID: " + id + " not found");
    }
}
