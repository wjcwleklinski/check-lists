package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.command.ProductCreateCommand;
import com.wjcwleklinski.shoppingserver.model.command.ProductUpdateCommand;
import com.wjcwleklinski.shoppingserver.model.command.ProductsDeleteCommand;
import com.wjcwleklinski.shoppingserver.model.view.ProductCollectionView;
import com.wjcwleklinski.shoppingserver.model.view.ProductDetailsView;
import com.wjcwleklinski.shoppingserver.repository.ProductRepository;
import com.wjcwleklinski.shoppingserver.repository.ShoppingListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService extends CommonService {

    private final ProductRepository productRepository;

    private final ShoppingListRepository shoppingListRepository;

    public void createNewProduct(String listCode, ProductCreateCommand command) {
        ShoppingList shoppingList = (ShoppingList) getByCode(listCode, shoppingListRepository);
        Product product = new Product();
        product.setCode(command.getCode());
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setPriority(command.getPriority());
        product.setImage(command.getImage());
        product.setShoppingList(shoppingList);
        save(product, productRepository);
        if (shoppingList.getProducts() == null) {
            shoppingList.setProducts(new ArrayList<>());
        }
        shoppingList.getProducts().add(product);
    }

    public List<ProductCollectionView> getProductsInList(String listCode) {
        return productRepository.findProductsByShoppingListCode(listCode).stream()
                .map(ProductCollectionView::getInstance)
                .collect(Collectors.toList());
    }

    public ProductDetailsView getProductByCode(String productCode) {
        return ProductDetailsView.getInstance((Product) getByCode(productCode, productRepository));
    }

    public void updateProduct(String productCode, ProductUpdateCommand command) {
        Product product = (Product) getByCode(productCode, productRepository);
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setPriority(command.getPriority());
        product.setImage(command.getImage());
        productRepository.save(product);
    }

    public void deleteProduct(String listCode, ProductsDeleteCommand command) {
        ShoppingList shoppingList = (ShoppingList) getByCode(listCode, shoppingListRepository);
        List<Product> products = shoppingList.getProducts().stream()
                .filter(product -> command.getProductCodes().contains(product.getCode()))
                .collect(Collectors.toList());
        productRepository.deleteInBatch(products);
    }
}
