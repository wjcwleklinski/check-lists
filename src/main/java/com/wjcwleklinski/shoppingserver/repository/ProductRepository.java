package com.wjcwleklinski.shoppingserver.repository;

import com.wjcwleklinski.shoppingserver.model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends CommonRepository<Product> {

    List<Product> findProductsByShoppingListCode(String listCode);
}
