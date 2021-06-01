package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.model.command.*;
import com.wjcwleklinski.shoppingserver.model.view.ProductCollectionView;
import com.wjcwleklinski.shoppingserver.model.view.ProductDetailsView;
import com.wjcwleklinski.shoppingserver.model.view.ShoppingListDetailsView;
import com.wjcwleklinski.shoppingserver.repository.ProductRepository;
import com.wjcwleklinski.shoppingserver.repository.ShoppingListRepository;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListService extends CommonService {

    private final ShoppingListRepository shoppingListRepository;

    private final ProductRepository productRepository;

    public List<ShoppingListCollectionProjection> getShoppingLists() {
        return shoppingListRepository.findAllShoppingListsBy();
    }

    public ShoppingListDetailsView getShoppingListDetails(String listCode) {
        ShoppingList shoppingList = (ShoppingList) getByCode(listCode, shoppingListRepository);
        return ShoppingListDetailsView.getInstance(shoppingList);
    }

    public void createShoppingList(ShoppingListCreateCommand command) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setCode(command.getCode());
        shoppingList.setName(command.getName());
        shoppingList.setDescription(command.getDescription());
        shoppingList.setImage(command.getImage());
        save(shoppingList, shoppingListRepository);
    }

    public void updateShoppingList(String listCode, ShoppingListUpdateCommand command) {
        ShoppingList shoppingList = (ShoppingList) getByCode(listCode, shoppingListRepository);
        shoppingList.setName(command.getName());
        shoppingList.setDescription(command.getDescription());
        shoppingList.setImage(command.getImage());
        save(shoppingList, shoppingListRepository);
    }

    public void deleteShoppingList(String listCode) {
        deleteByCode(listCode, shoppingListRepository);
    }
}
