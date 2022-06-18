package com.wjcwleklinski.listservice.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wjcwleklinski.listservice.common.validation.OneOfEnum;
import com.wjcwleklinski.listservice.model.entity.Entry;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EntryUpdateCommand {

    @JsonIgnore
    private String entryCode;

    @NotNull
    @OneOfEnum(enumClass = Entry.Priority.class)
    private String priority;

    private String name;

    private String description;

    private String image;
}
