package com.wjcwleklinski.shoppingserver.model.command;

import lombok.Data;

@Data
public class ShoppingListCreateCommand {

    private String name;

    private String description;

    private String image;

}
