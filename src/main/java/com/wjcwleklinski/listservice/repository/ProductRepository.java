package com.wjcwleklinski.listservice.repository;

import com.wjcwleklinski.listservice.model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends CommonRepository<Product> {

    List<Product> findProductsByShoppingListCode(String listCode);
}
