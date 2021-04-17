package com.wjcwleklinski.shoppingserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SPL_SHOPPING_LISTS")
@Getter
@Setter
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPL_ID")
    private Long id;

    @Column(name = "SPL_CODE")
    private String code;

    @Column(name = "SPL_NAME")
    private String name;

    @Column(name = "SPL_DESCRIPTION")
    private String description;

    @Column(name = "SPL_IMAGE")
    private String image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PRD_SPL_ID", referencedColumnName = "SPL_ID")
    private List<Product> products;
}
