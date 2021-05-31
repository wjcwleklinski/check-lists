package com.wjcwleklinski.shoppingserver.error.exception;

public class ConflictException extends RuntimeException {

    private static final String message = "Entity of code: %s already exists.";

    public ConflictException(String entityCode) {
        super(String.format(message, entityCode));
    }
}
