package com.wjcwleklinski.shoppingserver.error;

import com.wjcwleklinski.shoppingserver.error.exception.DuplicatedCodeException;
import com.wjcwleklinski.shoppingserver.error.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ShoppingServerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicatedCodeException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicatedCode(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.buildErrorResponse(HttpStatus.BAD_REQUEST,
                exception.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFound(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.buildErrorResponse(HttpStatus.NOT_FOUND,
                exception.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI());
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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
