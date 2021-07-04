package com.wjcwleklinski.shoppingserver.error;

import com.wjcwleklinski.shoppingserver.error.exception.ConflictException;
import com.wjcwleklinski.shoppingserver.error.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class ShoppingServerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    protected ResponseEntity<ErrorResponse> handleConflict(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.buildErrorResponse(HttpStatus.CONFLICT,
                exception.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFound(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.buildErrorResponse(HttpStatus.NOT_FOUND,
                exception.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleOtherErrors(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message("Shopping server error")
                .path(( (ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.name())
                .message(ex.getLocalizedMessage())
                .path(( (ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        log.error(ex.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }
}
