package com.wjcwleklinski.shoppingserver.model;

import com.wjcwleklinski.shoppingserver.model.entity.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SPL_SHOPPING_LISTS")
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "code", column = @Column(name = "SPL_CODE"))
})
public class ShoppingList extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPL_SEQUENCE")
    @SequenceGenerator(name = "SPL_SEQUENCE", sequenceName = "SPL_SEQUENCE")
    @Column(name = "SPL_ID")
    private Long id;

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
