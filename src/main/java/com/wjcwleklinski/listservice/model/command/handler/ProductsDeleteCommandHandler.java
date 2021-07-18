package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.Product;
import com.wjcwleklinski.listservice.model.ShoppingList;
import com.wjcwleklinski.listservice.model.command.ProductsDeleteCommand;
import com.wjcwleklinski.listservice.repository.ProductRepository;
import com.wjcwleklinski.listservice.repository.ShoppingListRepository;
import com.wjcwleklinski.listservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
