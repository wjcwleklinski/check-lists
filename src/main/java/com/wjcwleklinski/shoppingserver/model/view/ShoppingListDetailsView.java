package com.wjcwleklinski.shoppingserver.model.view;

import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(staticName = "getInstance", access = AccessLevel.PROTECTED)
public class ShoppingListDetailsView {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String image;

    private List<String> namesOfProducts;

    private List<String> codesOfProducts;

    public static ShoppingListDetailsView getInstance(ShoppingList shoppingList) {
        return getInstance().fillProperties(shoppingList);
    }

    private ShoppingListDetailsView fillProperties(ShoppingList shoppingList) {
        this.setId(shoppingList.getId());
        this.setName(shoppingList.getName());
        this.setCode(shoppingList.getCode());
        this.setDescription(shoppingList.getDescription());
        this.setImage(shoppingList.getImage());
        this.setCodesOfProducts(shoppingList.getProducts()
                .stream()
                .map(Product::getCode)
                .collect(Collectors.toList())
        );
        this.setNamesOfProducts(shoppingList.getProducts()
                .stream()
                .map(Product::getName)
                .collect(Collectors.toList())
        );
        return this;
    }
}
