package com.wjcwleklinski.listservice.error.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityCode) {
        super("Entity of code: "  + entityCode + " not found.");
    }

    public NotFoundException(Long entityId) {
        super("Entity of id: "  + entityId + " not found.");
    }
}
