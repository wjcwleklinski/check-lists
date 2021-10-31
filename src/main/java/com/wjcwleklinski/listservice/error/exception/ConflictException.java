package com.wjcwleklinski.listservice.error.exception;

import com.wjcwleklinski.listservice.error.ErrorMessage;

public class ConflictException extends RuntimeException {

    public ConflictException(String entityCode) {
        super(ErrorMessage.ENTITY_OF_CODE_ALREADY_EXISTS.getMessage(entityCode));
    }
}
