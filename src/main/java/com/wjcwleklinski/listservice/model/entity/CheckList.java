package com.wjcwleklinski.listservice.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CHL_CHECK_LISTS")
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "code", column = @Column(name = "CHL_CODE")),
        @AttributeOverride(name = "id", column = @Column(name = "CHL_ID"))
})
@SequenceGenerator(name = "COMMON_GEN", sequenceName = "CHL_SEQUENCE")
@Builder
public class CheckList extends CommonEntity {

    @Column(name = "CHL_NAME")
    private String name;

    @Column(name = "CHL_DESCRIPTION")
    private String description;

    @Column(name = "CHL_IMAGE")
    private String image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ETR_CHL_ID", referencedColumnName = "CHL_ID")
    private List<Entry> entries;
}
