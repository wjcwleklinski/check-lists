package com.wjcwleklinski.shoppingserver.model.view;

import lombok.Data;


@Data
public class ProductCollectionView {

    private String code;

    private String priority;

    private String name;

    private String description;

    private String image;

    private String shoppingListCode;

    private String shoppingListName;
}
