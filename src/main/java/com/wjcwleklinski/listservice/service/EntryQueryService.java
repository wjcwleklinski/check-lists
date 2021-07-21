package com.wjcwleklinski.listservice.service;

import com.wjcwleklinski.listservice.model.Entry;
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

    public List<EntryCollectionView> getProductsInList(String listCode) {
        return entryRepository.findProductsByShoppingListCode(listCode).stream()
                .map(EntryCollectionView::getInstance)
                .collect(Collectors.toList());
    }

    public EntryDetailsView getProductByCode(String productCode) {
        return EntryDetailsView.getInstance((Entry) commonService.getByCode(productCode, entryRepository));
    }
}
