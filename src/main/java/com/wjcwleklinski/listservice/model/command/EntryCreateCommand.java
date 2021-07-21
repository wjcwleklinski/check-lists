package com.wjcwleklinski.listservice.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wjcwleklinski.listservice.model.Entry;
import com.wjcwleklinski.listservice.common.validation.OneOfEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EntryCreateCommand {

    @JsonIgnore
    private String listCode;

    private String code;

    @NotBlank
    @OneOfEnum(enumClass = Entry.Priority.class)
    private String priority;

    @NotBlank
    private String name;

    private String description;

    private String image;
}
