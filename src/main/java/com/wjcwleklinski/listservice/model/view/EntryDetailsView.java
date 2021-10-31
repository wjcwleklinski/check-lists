package com.wjcwleklinski.listservice.model.view;

import com.wjcwleklinski.listservice.model.entity.Entry;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(staticName = "getInstance")
public class EntryDetailsView extends EntryCollectionView {

    private String description;

    private String checkListCode;

    private String checkListName;

    public static EntryDetailsView getInstance(Entry entry) {
        return getInstance().fillProperties(entry);
    }

    protected EntryDetailsView fillProperties(Entry entry) {
        super.fillProperties(entry);
        this.setDescription(entry.getDescription());
        this.setCheckListCode(entry.getCheckList().getCode());
        this.setCheckListName(entry.getCheckList().getName());
        return this;
    }
}
