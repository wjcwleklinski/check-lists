package com.wjcwleklinski.shoppingserver.resource;

import com.wjcwleklinski.shoppingserver.common.handler.CommandProcessor;
import com.wjcwleklinski.shoppingserver.model.command.ProductCreateCommand;
import com.wjcwleklinski.shoppingserver.model.command.ProductUpdateCommand;
import com.wjcwleklinski.shoppingserver.model.command.ProductsDeleteCommand;
import com.wjcwleklinski.shoppingserver.model.view.ProductCollectionView;
import com.wjcwleklinski.shoppingserver.model.view.ProductDetailsView;
import com.wjcwleklinski.shoppingserver.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shopping-lists/{listCode}/products")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductQueryService productQueryService;

    private final CommandProcessor commandProcessor;

    @GetMapping
    public List<ProductCollectionView> getProducts(@PathVariable("listCode") String listCode) {
        return productQueryService.getProductsInList(listCode);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@PathVariable("listCode") String listCode,
                                           @Valid @RequestBody ProductCreateCommand command) {
        command.setListCode(listCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productCode}")
    public ProductDetailsView getProductDetails(@PathVariable("productCode") String productCode) {
        return productQueryService.getProductByCode(productCode);
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<?> updateSProduct(@PathVariable("productCode") String productCode,
                                            @RequestBody ProductUpdateCommand command) {
        command.setProductCode(productCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProducts(@PathVariable("listCode") String listCode,
                                           @RequestBody ProductsDeleteCommand command) {
        command.setListCode(listCode);
        commandProcessor.process(command);
        return ResponseEntity.ok().build();
    }
}
