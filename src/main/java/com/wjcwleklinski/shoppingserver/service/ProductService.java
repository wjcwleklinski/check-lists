package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.model.Product;
import com.wjcwleklinski.shoppingserver.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


}
