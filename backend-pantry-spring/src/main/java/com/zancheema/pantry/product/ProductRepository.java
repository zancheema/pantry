package com.zancheema.pantry.product;

import com.zancheema.pantry.product.Product.ProductId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {
    Iterable<Product> findAllByProductIdUserUsername(String username);

    Optional<Product> findByProductId(ProductId productId);
}
