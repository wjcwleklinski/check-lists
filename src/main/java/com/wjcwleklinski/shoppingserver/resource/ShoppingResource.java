package com.wjcwleklinski.shoppingserver.resource;

import com.wjcwleklinski.shoppingserver.ShoppingListRepository;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoppingResource {

    @Autowired
    ShoppingListRepository shoppingListRepository;

    @GetMapping("/shopping-lists")
    public List<ShoppingList> getShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListRepository.findAll();
        return shoppingLists;
    }
}
