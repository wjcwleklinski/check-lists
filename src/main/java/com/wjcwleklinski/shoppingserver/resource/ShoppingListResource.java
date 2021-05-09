package com.wjcwleklinski.shoppingserver.resource;

import com.wjcwleklinski.shoppingserver.model.command.*;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionProjection;
import com.wjcwleklinski.shoppingserver.model.view.ProductCollectionView;
import com.wjcwleklinski.shoppingserver.model.view.ProductDetailsView;
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

    @PostMapping
    public ResponseEntity<?> createShoppingList(@RequestBody ShoppingListCreateCommand command) {
        shoppingListService.createShoppingList(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{listId}")
    public ShoppingListDetailsView getShoppingList(@PathVariable("listId") Long listId) {
        return shoppingListService.getShoppingListDetails(listId);
    }

    @PutMapping("/{listId}")
    public ResponseEntity<?> updateShoppingList(@PathVariable("listId") Long listId,
                                                @RequestBody ShoppingListUpdateCommand command) {
        shoppingListService.updateShoppingList(listId, command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<?> deleteShoppingList(@PathVariable("listId") Long listId) {
        shoppingListService.deleteShoppingList(listId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{listId}/products")
    public List<ProductCollectionView> getProducts(@PathVariable("listId") Long listId) {
        return shoppingListService.getProductsInList(listId);
    }

    @PostMapping("/{listId}/products")
    public ResponseEntity<?> createProduct(@PathVariable("listId") Long listId,
                                           @RequestBody ProductCreateCommand command) {
        shoppingListService.createNewProduct(listId, command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{listId}/products/{productId}")
    public ProductDetailsView getProductDetails(@PathVariable("productId") Long productId) {
        return shoppingListService.getProductById(productId);
    }

    @PutMapping("/{listId}/products/{productId}")
    public ResponseEntity<?> updateSProduct(@PathVariable("productId") Long productId,
                                            @RequestBody ProductUpdateCommand command) {
        shoppingListService.updateProduct(productId, command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{listId}/products")
    public ResponseEntity<?> deleteProduct(@PathVariable("listId") Long listId,
                                           @RequestBody ProductsDeleteCommand command) {
        shoppingListService.deleteProduct(listId, command);
        return ResponseEntity.ok().build();
    }

}
