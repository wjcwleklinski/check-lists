package com.wjcwleklinski.shoppingserver.resource;

import com.wjcwleklinski.shoppingserver.common.handler.CommandProcessor;
import com.wjcwleklinski.shoppingserver.model.command.*;
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

    private final CommandProcessor commandProcessor;

    @GetMapping
    public List<ShoppingListCollectionProjection> getShoppingLists() {
        return shoppingListService.getShoppingLists();
    }

    @PostMapping
    public ResponseEntity<?> createShoppingList(@RequestBody ShoppingListCreateCommand command) {
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{listCode}")
    public ShoppingListDetailsView getShoppingList(@PathVariable("listCode") String listCode) {
        return shoppingListService.getShoppingListDetails(listCode);
    }

    @PutMapping("/{listCode}")
    public ResponseEntity<?> updateShoppingList(@PathVariable("listCode") String listCode,
                                                @RequestBody ShoppingListUpdateCommand command) {
        shoppingListService.updateShoppingList(listCode, command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{listCode}")
    public ResponseEntity<?> deleteShoppingList(@PathVariable("listCode") String listCode) {
        shoppingListService.deleteShoppingList(listCode);
        return ResponseEntity.ok().build();
    }
}
