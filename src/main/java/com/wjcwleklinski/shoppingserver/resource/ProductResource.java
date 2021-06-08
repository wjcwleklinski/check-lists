package com.wjcwleklinski.shoppingserver.resource;

import com.wjcwleklinski.shoppingserver.model.command.ProductCreateCommand;
import com.wjcwleklinski.shoppingserver.model.command.ProductUpdateCommand;
import com.wjcwleklinski.shoppingserver.model.command.ProductsDeleteCommand;
import com.wjcwleklinski.shoppingserver.model.view.ProductCollectionView;
import com.wjcwleklinski.shoppingserver.model.view.ProductDetailsView;
import com.wjcwleklinski.shoppingserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shopping-lists/{listCode}/products")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public List<ProductCollectionView> getProducts(@PathVariable("listCode") String listCode) {
        return productService.getProductsInList(listCode);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@PathVariable("listCode") String listCode,
                                           @Valid @RequestBody ProductCreateCommand command) {
        productService.createNewProduct(listCode, command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productCode}")
    public ProductDetailsView getProductDetails(@PathVariable("productCode") String productCode) {
        return productService.getProductByCode(productCode);
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<?> updateSProduct(@PathVariable("productCode") String productCode,
                                            @RequestBody ProductUpdateCommand command) {
        productService.updateProduct(productCode, command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@PathVariable("listCode") String listCode,
                                           @RequestBody ProductsDeleteCommand command) {
        productService.deleteProduct(listCode, command);
        return ResponseEntity.ok().build();
    }
}
