package com.wjcwleklinski.shoppingserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRD_PRODUCTS")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRD_ID")
    private Long id;

    @Column(name = "PRD_CODE")
    private String code;

    @Column(name = "PRD_PRIORITY")
    private String priority;

    @Column(name = "PRD_NAME")
    private String name;

    @Column(name = "PRD_DESCRIPTION")
    private String description;

    @Column(name = "PRD_IMAGE")
    private String image;

    @ManyToOne
    @JoinColumn(name = "PRD_SPL_ID")
    private ShoppingList shoppingList;
}
