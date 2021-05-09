package com.wjcwleklinski.shoppingserver.model.view;

import com.wjcwleklinski.shoppingserver.model.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(staticName = "getInstance", access = AccessLevel.PROTECTED)
public class ProductDetailsView extends ProductCollectionView {

    private String description;

    private String shoppingListCode;

    private String shoppingListName;

    public static ProductDetailsView getInstance(Product product) {
        return getInstance().fillProperties(product);
    }

    protected ProductDetailsView fillProperties(Product product) {
        super.fillProperties(product);
        this.setDescription(product.getDescription());
        this.setShoppingListCode(product.getShoppingList().getCode());
        this.setShoppingListName(product.getShoppingList().getName());
        return this;
    }
}
