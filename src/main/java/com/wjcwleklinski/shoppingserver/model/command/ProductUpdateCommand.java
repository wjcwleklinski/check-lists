package com.wjcwleklinski.shoppingserver.model.command;

import lombok.Data;

@Data
public class ProductUpdateCommand {

    private String priority;

    private String name;

    private String description;

    private String image;
}
