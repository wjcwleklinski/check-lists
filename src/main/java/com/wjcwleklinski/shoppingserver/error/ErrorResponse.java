package com.wjcwleklinski.shoppingserver.error;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorResponse {

    private LocalDateTime timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

    public static ErrorResponse buildErrorResponse(HttpStatus status, String message, WebRequest request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.name())
                .message(message)
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
    }
}
