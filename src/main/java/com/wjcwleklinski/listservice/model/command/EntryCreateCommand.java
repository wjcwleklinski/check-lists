package com.wjcwleklinski.listservice.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wjcwleklinski.listservice.model.entity.Entry;
import com.wjcwleklinski.listservice.common.validation.OneOfEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EntryCreateCommand {

    @JsonIgnore
    private String listCode;

    private String code;

    @NotNull
    @OneOfEnum(enumClass = Entry.Priority.class)
    private String priority;

    @NotBlank
    private String name;

    private String description;

    private String image;
}
