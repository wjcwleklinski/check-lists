package com.wjcwleklinski.shoppingserver.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class CommonEntity {

    @Column(name = "CODE")
    private String code;
}
