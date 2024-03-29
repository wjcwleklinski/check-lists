package com.wjcwleklinski.listservice.model.command;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class CheckListUpdateCommand {

    @JsonIgnore
    private String listCode;

    private String name;

    private String description;

    private String image;
}
