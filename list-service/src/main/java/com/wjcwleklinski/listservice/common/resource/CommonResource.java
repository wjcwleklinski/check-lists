package com.wjcwleklinski.listservice.common.resource;

import com.wjcwleklinski.listservice.error.ErrorMessage;
import com.wjcwleklinski.listservice.error.exception.InternalServerException;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

public class CommonResource {

    private final String URL_SEPARATOR = "/";

    protected ResponseEntity<?> created(HttpServletRequest request, String commandResult) {
        try {
            return ResponseEntity.created(new URI(request.getRequestURI() + URL_SEPARATOR + commandResult)).build();
        } catch (URISyntaxException e) {
            throw new InternalServerException(ErrorMessage.INVALID_URI.getMessage());
        }
    }
}
