package com.wjcwleklinski.shoppingserver.model.command;
import lombok.Data;


@Data
public class ShoppingListUpdateCommand {

    private String name;

    private String description;

    private String image;
}
