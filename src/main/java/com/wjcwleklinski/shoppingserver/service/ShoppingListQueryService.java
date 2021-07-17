package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.model.view.ShoppingListDetailsView;
import com.wjcwleklinski.shoppingserver.repository.ShoppingListRepository;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingListQueryService {

    private final ShoppingListRepository shoppingListRepository;

    private final CommonService commonService;

    public List<ShoppingListCollectionProjection> getShoppingLists() {
        return shoppingListRepository.findAllShoppingListsBy();
    }

    public ShoppingListDetailsView getShoppingListDetails(String listCode) {
        ShoppingList shoppingList = (ShoppingList) commonService.getByCode(listCode, shoppingListRepository);
        return ShoppingListDetailsView.getInstance(shoppingList);
    }

    public void deleteShoppingList(String listCode) {
        commonService.deleteByCode(listCode, shoppingListRepository);
    }
}
