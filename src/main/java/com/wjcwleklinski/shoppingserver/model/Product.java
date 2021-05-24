package com.wjcwleklinski.shoppingserver.model;

import com.wjcwleklinski.shoppingserver.model.entity.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRD_PRODUCTS")
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "code", column = @Column(name = "PRD_CODE")),
        @AttributeOverride(name = "id", column = @Column(name = "PRD_ID"))
})
@SequenceGenerator(name = "COMMON_GEN", sequenceName = "SPL_SEQUENCE")
public class Product extends CommonEntity {

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
