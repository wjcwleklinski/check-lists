package com.wjcwleklinski.listservice.model.view;

import com.wjcwleklinski.listservice.model.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProductCollectionView {

    private String code;

    private String priority;

    private String name;

    private String image;

    public static ProductCollectionView getInstance(Product product) {
        return new ProductCollectionView().fillProperties(product);
    }

    protected ProductCollectionView fillProperties(Product product) {
        this.setCode(product.getCode());
        this.setPriority(product.getPriority());
        this.setName(product.getName());
        this.setImage(product.getImage());
        return this;
    }
}
