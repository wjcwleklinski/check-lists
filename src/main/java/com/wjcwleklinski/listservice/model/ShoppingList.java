package com.wjcwleklinski.listservice.model;

import com.wjcwleklinski.listservice.model.entity.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SPL_SHOPPING_LISTS")
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "code", column = @Column(name = "SPL_CODE")),
        @AttributeOverride(name = "id", column = @Column(name = "SPL_ID"))
})
@SequenceGenerator(name = "COMMON_GEN", sequenceName = "SPL_SEQUENCE")
public class ShoppingList extends CommonEntity {

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
