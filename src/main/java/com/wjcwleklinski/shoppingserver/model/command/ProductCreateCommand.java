package com.wjcwleklinski.shoppingserver.model.command;

import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.common.validation.OneOfEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductCreateCommand {

    private String code;

    @NotBlank
    @OneOfEnum(enumClass = Product.Priority.class)
    private String priority;

    @NotBlank
    private String name;

    private String description;

    private String image;
}
