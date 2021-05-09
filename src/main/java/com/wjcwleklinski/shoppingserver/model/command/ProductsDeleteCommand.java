package com.wjcwleklinski.shoppingserver.model.command;

import lombok.Data;
import java.util.List;

@Data
public class ProductsDeleteCommand {

    private List<String> productCodes;
}
