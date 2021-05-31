package com.wjcwleklinski.shoppingserver.repository;

import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionProjection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends CommonRepository<ShoppingList> {

    List<ShoppingListCollectionProjection> findAllShoppingListsBy();
}
