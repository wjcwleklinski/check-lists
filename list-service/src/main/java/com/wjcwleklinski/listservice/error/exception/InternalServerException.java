package com.wjcwleklinski.listservice.error.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class InternalServerException extends RuntimeException {

    public InternalServerException(String logMessage) {
        super(logMessage);
        log.error(logMessage);
    }
}
