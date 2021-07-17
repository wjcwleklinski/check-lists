package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.model.view.ProductCollectionView;
import com.wjcwleklinski.shoppingserver.model.view.ProductDetailsView;
import com.wjcwleklinski.shoppingserver.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductRepository productRepository;

    private final CommonService commonService;

    public List<ProductCollectionView> getProductsInList(String listCode) {
        return productRepository.findProductsByShoppingListCode(listCode).stream()
                .map(ProductCollectionView::getInstance)
                .collect(Collectors.toList());
    }

    public ProductDetailsView getProductByCode(String productCode) {
        return ProductDetailsView.getInstance((Product) commonService.getByCode(productCode, productRepository));
    }
}
