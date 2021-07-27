package com.wjcwleklinski.listservice.error;

import com.wjcwleklinski.listservice.error.exception.ConflictException;
import com.wjcwleklinski.listservice.error.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;


@ControllerAdvice
@Log4j2
public class ListServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    protected ResponseEntity<ErrorResponse> handleConflict(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.buildErrorResponse(HttpStatus.CONFLICT,
                exception.getMessage(), request);
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFound(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.buildErrorResponse(HttpStatus.NOT_FOUND,
                exception.getMessage(), request);
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleOtherErrors(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Checklist service error", request);
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.buildErrorResponse(status,
                Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage(), request);
        log.error(ex.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }
}
