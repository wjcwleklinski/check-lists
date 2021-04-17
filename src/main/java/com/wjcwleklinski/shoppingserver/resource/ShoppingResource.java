package com.wjcwleklinski.shoppingserver.resource;

import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionView;
import com.wjcwleklinski.shoppingserver.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingResource {

    private final ShoppingService shoppingService;

    @GetMapping("/shopping-lists")
    public List<ShoppingListCollectionView> getShoppingLists() {
        return shoppingService.getShoppingLists();
    }

    @GetMapping("/shopping-lists/{id}")
    public ShoppingList getShoppingList(@PathVariable("id") Long id) {
        return shoppingService.getShoppingListDetails(id);
    }
}
