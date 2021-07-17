package com.wjcwleklinski.shoppingserver.model.command.handler;

import com.wjcwleklinski.shoppingserver.common.handler.CommandHandler;
import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.model.command.ProductUpdateCommand;
import com.wjcwleklinski.shoppingserver.repository.ProductRepository;
import com.wjcwleklinski.shoppingserver.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductUpdateCommandHandler implements CommandHandler<ProductUpdateCommand> {

    private final CommonService commonService;

    private final ProductRepository productRepository;

    @Override
    public void execute(ProductUpdateCommand command) {
        Product product = (Product) commonService.getByCode(command.getProductCode(), productRepository);
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setPriority(command.getPriority());
        product.setImage(command.getImage());
        productRepository.save(product);
    }
}
