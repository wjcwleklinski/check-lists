package com.wjcwleklinski.listservice.error.exception;

import com.wjcwleklinski.listservice.error.ErrorMessage;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityCode) {
        super(ErrorMessage.ENTITY_OF_CODE_NOT_FOUND.getMessage(entityCode));
    }

    public NotFoundException(Long entityId) {
        super(ErrorMessage.ENTITY_OF_ID_NOT_FOUND.getMessage(entityId));
    }
}
