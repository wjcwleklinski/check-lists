package com.wjcwleklinski.shoppingserver.error.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityCode) {
        super("Entity of code: "  + entityCode + " not found.");
    }
}
