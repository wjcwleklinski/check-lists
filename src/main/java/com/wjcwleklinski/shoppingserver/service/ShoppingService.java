package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.ShoppingListRepository;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    private final ShoppingListRepository shoppingListRepository;

    public List<ShoppingListCollectionView> getShoppingLists() {
        return shoppingListRepository.findAllShoppingListsBy();
    }

    public ShoppingList getShoppingListDetails(Long listId) {
        return shoppingListRepository.getListById(listId);
    }
}
