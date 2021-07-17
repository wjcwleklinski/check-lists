package com.wjcwleklinski.shoppingserver.model.command.handler;

import com.wjcwleklinski.shoppingserver.common.handler.CommandHandler;
import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.model.ShoppingList;
import com.wjcwleklinski.shoppingserver.model.command.ProductCreateCommand;
import com.wjcwleklinski.shoppingserver.model.command.ProductsDeleteCommand;
import com.wjcwleklinski.shoppingserver.repository.ProductRepository;
import com.wjcwleklinski.shoppingserver.repository.ShoppingListRepository;
import com.wjcwleklinski.shoppingserver.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductsDeleteCommandHandler implements CommandHandler<ProductsDeleteCommand> {

    private final CommonService commonService;

    private final ShoppingListRepository shoppingListRepository;

    private final ProductRepository productRepository;

    @Override
    public void execute(ProductsDeleteCommand command) {
        ShoppingList shoppingList = (ShoppingList) commonService.getByCode(command.getListCode(), shoppingListRepository);
        List<Product> products = shoppingList.getProducts().stream()
                .filter(product -> command.getProductCodes().contains(product.getCode()))
                .collect(Collectors.toList());
        productRepository.deleteInBatch(products);
    }
}
