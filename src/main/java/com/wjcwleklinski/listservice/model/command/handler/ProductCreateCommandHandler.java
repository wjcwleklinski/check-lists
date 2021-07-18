package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.Product;
import com.wjcwleklinski.listservice.model.ShoppingList;
import com.wjcwleklinski.listservice.model.command.ProductCreateCommand;
import com.wjcwleklinski.listservice.repository.ProductRepository;
import com.wjcwleklinski.listservice.repository.ShoppingListRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ProductCreateCommandHandler implements CommandHandler<ProductCreateCommand> {

    private final CommonService commonService;

    private final ShoppingListRepository shoppingListRepository;

    private final ProductRepository productRepository;

    @Override
    public void execute(ProductCreateCommand command) {
        ShoppingList shoppingList = (ShoppingList) commonService.getByCode(command.getListCode(), shoppingListRepository);
        Product product = new Product();
        product.setCode(command.getCode());
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setPriority(command.getPriority());
        product.setImage(command.getImage());
        product.setShoppingList(shoppingList);
        commonService.save(product, productRepository);
        if (shoppingList.getProducts() == null) {
            shoppingList.setProducts(new ArrayList<>());
        }
        shoppingList.getProducts().add(product);
    }
}
