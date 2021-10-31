package com.wjcwleklinski.listservice.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    ENTITY_OF_CODE_NOT_FOUND("Entity of code: %s not found."),
    ENTITY_OF_ID_NOT_FOUND("Entity of id: %s not found."),
    ENTITY_OF_CODE_ALREADY_EXISTS("Entity of code: %s already exists."),
    COMMAND_HANDLER_NOT_FOUND("Handler not found for command: %s"),
    FILE_ERROR("Could not store a file"),
    CHECKLIST_NOT_EMPTY("Checklist contains entries.");

    String message;

    public String getMessage(Object... params) {
        return String.format(message, params);
    }

}
