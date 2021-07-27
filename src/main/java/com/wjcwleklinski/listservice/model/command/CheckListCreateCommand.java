package com.wjcwleklinski.listservice.model.command;

import lombok.Data;

@Data
public class CheckListCreateCommand {

    private String code;

    private String name;

    private String description;

    private String image;

}
