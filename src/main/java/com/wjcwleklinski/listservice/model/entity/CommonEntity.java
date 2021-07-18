package com.wjcwleklinski.listservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMON_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE", unique = true)
    private String code;
}
