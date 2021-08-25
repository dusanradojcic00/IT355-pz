package com.met.it355pz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchElementFoundException extends RuntimeException {

    public NoSuchElementFoundException(String element) {
        super("Entity not found: " + element);
    }

    public NoSuchElementFoundException(Long id) {
        super("Entitiy with ID: " + id + " not found");
    }
}
