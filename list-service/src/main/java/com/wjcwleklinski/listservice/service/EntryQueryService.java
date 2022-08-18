package com.wjcwleklinski.listservice.service;

import com.wjcwleklinski.listservice.model.entity.Entry;
import com.wjcwleklinski.listservice.model.view.EntryCollectionView;
import com.wjcwleklinski.listservice.model.view.EntryDetailsView;
import com.wjcwleklinski.listservice.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryQueryService {

    private final EntryRepository entryRepository;

    private final CommonService commonService;

    public List<EntryCollectionView> getEntriesInList(String listCode) {
        return entryRepository.findEntriesByCheckListCode(listCode).stream()
                .map(EntryCollectionView::getInstance)
                .collect(Collectors.toList());
    }

    public EntryDetailsView getEntryByCode(String productCode) {
        return EntryDetailsView.getInstance((Entry) commonService.getByCode(productCode, entryRepository));
    }
}
