package com.wjcwleklinski.listservice.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

@Data
public class EntryDeleteCommand {

    @JsonIgnore
    private String listCode;

    private List<String> entryCodes;
}
