package com.wjcwleklinski.shoppingserver.resource;

import com.wjcwleklinski.shoppingserver.common.handler.CommandProcessor;
import com.wjcwleklinski.shoppingserver.model.command.*;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionProjection;
import com.wjcwleklinski.shoppingserver.model.view.ShoppingListDetailsView;
import com.wjcwleklinski.shoppingserver.service.ShoppingListQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-lists")
@RequiredArgsConstructor
public class ShoppingListResource {

    private final ShoppingListQueryService shoppingListQueryService;

    private final CommandProcessor commandProcessor;

    @GetMapping
    public List<ShoppingListCollectionProjection> getShoppingLists() {
        return shoppingListQueryService.getShoppingLists();
    }

    @PostMapping
    public ResponseEntity<?> createShoppingList(@RequestBody ShoppingListCreateCommand command) {
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{listCode}")
    public ShoppingListDetailsView getShoppingList(@PathVariable("listCode") String listCode) {
        return shoppingListQueryService.getShoppingListDetails(listCode);
    }

    @PutMapping("/{listCode}")
    public ResponseEntity<?> updateShoppingList(@PathVariable("listCode") String listCode,
                                                @RequestBody ShoppingListUpdateCommand command) {
        command.setListCode(listCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{listCode}")
    public ResponseEntity<?> deleteShoppingList(@PathVariable("listCode") String listCode) {
        shoppingListQueryService.deleteShoppingList(listCode);
        return ResponseEntity.ok().build();
    }
}
