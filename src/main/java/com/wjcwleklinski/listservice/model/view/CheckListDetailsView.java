package com.wjcwleklinski.listservice.model.view;

import com.wjcwleklinski.listservice.model.entity.Entry;
import com.wjcwleklinski.listservice.model.entity.CheckList;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(staticName = "getInstance", access = AccessLevel.PROTECTED)
public class CheckListDetailsView {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String image;

    private List<String> namesOfEntries;

    private List<String> codesOfEntries;

    public static CheckListDetailsView getInstance(CheckList checkList) {
        return getInstance().fillProperties(checkList);
    }

    private CheckListDetailsView fillProperties(CheckList checkList) {
        this.setId(checkList.getId());
        this.setName(checkList.getName());
        this.setCode(checkList.getCode());
        this.setDescription(checkList.getDescription());
        this.setImage(checkList.getImage());
        this.setCodesOfEntries(checkList.getEntries()
                .stream()
                .map(Entry::getCode)
                .collect(Collectors.toList())
        );
        this.setNamesOfEntries(checkList.getEntries()
                .stream()
                .map(Entry::getName)
                .collect(Collectors.toList())
        );
        return this;
    }
}
