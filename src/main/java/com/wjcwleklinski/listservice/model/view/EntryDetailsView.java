package com.wjcwleklinski.listservice.model.view;

import com.wjcwleklinski.listservice.model.Entry;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(staticName = "getInstance")
public class EntryDetailsView extends EntryCollectionView {

    private String description;

    private String shoppingListCode;

    private String shoppingListName;

    public static EntryDetailsView getInstance(Entry entry) {
        return getInstance().fillProperties(entry);
    }

    protected EntryDetailsView fillProperties(Entry entry) {
        super.fillProperties(entry);
        this.setDescription(entry.getDescription());
        this.setShoppingListCode(entry.getCheckList().getCode());
        this.setShoppingListName(entry.getCheckList().getName());
        return this;
    }
}
