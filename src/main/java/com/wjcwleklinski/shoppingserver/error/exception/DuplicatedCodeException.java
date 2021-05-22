package com.wjcwleklinski.shoppingserver.error.exception;

public class DuplicatedCodeException extends RuntimeException {

    public DuplicatedCodeException(String duplicatedCode) {
        super("Code: " + duplicatedCode + " is already used.");
    }
}
