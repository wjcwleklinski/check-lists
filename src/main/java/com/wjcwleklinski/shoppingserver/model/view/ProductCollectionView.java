package com.wjcwleklinski.shoppingserver.model.view;

import com.wjcwleklinski.shoppingserver.model.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(staticName = "getInstance", access = AccessLevel.PROTECTED)
public class ProductCollectionView {

    private String code;

    private String priority;

    private String name;

    private String image;

    public static ProductCollectionView getInstance(Product product) {
        return getInstance().fillProperties(product);
    }

    protected ProductCollectionView fillProperties(Product product) {
        this.setCode(product.getCode());
        this.setPriority(product.getPriority());
        this.setName(product.getName());
        this.setImage(product.getImage());
        return this;
    }
}
