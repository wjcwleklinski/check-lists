package com.wjcwleklinski.listservice.model.view;

import com.wjcwleklinski.listservice.model.entity.Entry;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EntryCollectionView {

    private String code;

    private String priority;

    private String name;

    private String image;

    public static EntryCollectionView getInstance(Entry entry) {
        return new EntryCollectionView().fillProperties(entry);
    }

    protected EntryCollectionView fillProperties(Entry entry) {
        this.setCode(entry.getCode());
        this.setPriority(entry.getPriority());
        this.setName(entry.getName());
        this.setImage(entry.getImage());
        return this;
    }
}
