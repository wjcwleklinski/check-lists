package com.wjcwleklinski.shoppingserver.resource;

import com.wjcwleklinski.shoppingserver.model.command.ProductCreateCommand;
import com.wjcwleklinski.shoppingserver.model.command.ShoppingListCreateCommand;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionProjection;
import com.wjcwleklinski.shoppingserver.model.view.ShoppingListDetailsView;
import com.wjcwleklinski.shoppingserver.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-lists")
@RequiredArgsConstructor
public class ShoppingListResource {

    private final ShoppingListService shoppingListService;

    @GetMapping
    public List<ShoppingListCollectionProjection> getShoppingLists() {
        return shoppingListService.getShoppingLists();
    }

    @GetMapping("/{id}")
    public ShoppingListDetailsView getShoppingList(@PathVariable("id") Long id) {
        return shoppingListService.getShoppingListDetails(id);
    }

    @PostMapping
    public ResponseEntity<?> createShoppingList(@RequestBody ShoppingListCreateCommand command) {
        shoppingListService.createShoppingList(command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<?> createNewProduct(@PathVariable("id") Long shoppingListId,
                                              @RequestBody ProductCreateCommand command) {
        shoppingListService.createNewProduct(shoppingListId, command);
        return ResponseEntity.ok().build();
    }
}
