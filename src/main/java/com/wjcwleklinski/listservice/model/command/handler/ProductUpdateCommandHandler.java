package com.wjcwleklinski.listservice.model.command.handler;

import com.wjcwleklinski.listservice.common.handler.CommandHandler;
import com.wjcwleklinski.listservice.model.Product;
import com.wjcwleklinski.listservice.model.command.ProductUpdateCommand;
import com.wjcwleklinski.listservice.repository.ProductRepository;
import com.wjcwleklinski.listservice.service.CommonService;
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
