package com.wjcwleklinski.shoppingserver.model.command;

import lombok.Data;

@Data
public class ProductCreateCommand {

    private String priority;

    private String name;

    private String description;

    private String image;
}
