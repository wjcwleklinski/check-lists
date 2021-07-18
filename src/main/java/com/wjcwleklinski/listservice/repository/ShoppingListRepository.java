package com.wjcwleklinski.listservice.repository;

import com.wjcwleklinski.listservice.model.ShoppingList;
import com.wjcwleklinski.listservice.model.projection.ShoppingListCollectionProjection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends CommonRepository<ShoppingList> {

    List<ShoppingListCollectionProjection> findAllShoppingListsBy();
}
