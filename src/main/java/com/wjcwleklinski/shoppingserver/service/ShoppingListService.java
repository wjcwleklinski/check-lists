package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.model.command.*;
import com.wjcwleklinski.shoppingserver.model.view.ProductCollectionView;
import com.wjcwleklinski.shoppingserver.model.view.ProductDetailsView;
import com.wjcwleklinski.shoppingserver.model.view.ShoppingListDetailsView;
import com.wjcwleklinski.shoppingserver.repository.ProductRepository;
import com.wjcwleklinski.shoppingserver.repository.ShoppingListRepository;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.projection.ShoppingListCollectionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListService extends CommonService {

    private final ShoppingListRepository shoppingListRepository;

    private final ProductRepository productRepository;

    public List<ShoppingListCollectionProjection> getShoppingLists() {
        return shoppingListRepository.findAllShoppingListsBy();
    }

    public ShoppingListDetailsView getShoppingListDetails(String listCode) {
        ShoppingList shoppingList = (ShoppingList) getByCode(listCode, shoppingListRepository);
        return ShoppingListDetailsView.getInstance(shoppingList);
    }

    public void createShoppingList(ShoppingListCreateCommand command) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setCode(command.getCode());
        shoppingList.setName(command.getName());
        shoppingList.setDescription(command.getDescription());
        shoppingList.setImage(command.getImage());
        save(shoppingList, shoppingListRepository);
    }

    public void createNewProduct(Long shoppingListId, ProductCreateCommand command) {
        ShoppingList shoppingList = shoppingListRepository.getListById(shoppingListId);
        Product product = new Product();
        product.setCode("code_" + command.getName());
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setPriority(command.getPriority());
        product.setShoppingList(shoppingList);
        productRepository.save(product);
        if (shoppingList.getProducts() == null) {
            shoppingList.setProducts(new ArrayList<>());
        }
        shoppingList.getProducts().add(product);
    }

    public List<ProductCollectionView> getProductsInList(Long listId) {
        return productRepository.findProductsByShoppingListId(listId).stream()
                .map(ProductCollectionView::getInstance)
                .collect(Collectors.toList());
    }

    public ProductDetailsView getProductById(Long productId) {
        return ProductDetailsView.getInstance(productRepository.getProductById(productId));
    }

    public void updateShoppingList(Long listId, ShoppingListUpdateCommand command) {
        ShoppingList shoppingList = shoppingListRepository.getListById(listId);
        shoppingList.setName(command.getName());
        shoppingList.setDescription(command.getDescription());
        shoppingList.setImage(command.getImage());
        shoppingListRepository.save(shoppingList);
    }

    public void deleteShoppingList(Long listId) {
        shoppingListRepository.deleteById(listId);
    }

    public void updateProduct(Long productId, ProductUpdateCommand command) {
        Product product = productRepository.getProductById(productId);
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setPriority(command.getPriority());
        product.setImage(command.getImage());
        productRepository.save(product);
    }

    public void deleteProduct(Long listId, ProductsDeleteCommand command) {
        ShoppingList shoppingList = shoppingListRepository.getListById(listId);
        List<Product> products = shoppingList.getProducts().stream()
                .filter(product -> command.getProductCodes().contains(product.getCode()))
                .collect(Collectors.toList());
        productRepository.deleteInBatch(products);
    }
}
