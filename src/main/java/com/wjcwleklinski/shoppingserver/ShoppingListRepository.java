package com.wjcwleklinski.shoppingserver;

import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    List<ShoppingListCollectionView> findAllShoppingListsBy();

    ShoppingList getListById(Long id);

}
