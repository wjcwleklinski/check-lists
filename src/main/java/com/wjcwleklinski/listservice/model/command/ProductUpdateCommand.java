package com.wjcwleklinski.listservice.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProductUpdateCommand {

    @JsonIgnore
    private String productCode;

    private String priority;

    private String name;

    private String description;

    private String image;
}
