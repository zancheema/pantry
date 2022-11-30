package com.zancheema.pantry.productdetail;

import com.zancheema.pantry.product.Product;
import com.zancheema.pantry.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductDetailRepository extends CrudRepository<ProductDetail, Long> {
    Optional<ProductDetail> findByProductAndUser(Product product, User user);

    Iterable<ProductDetail> findAllByUser(User user);

    Optional<ProductDetail> findByProductBarcode(String barcode);

    Optional<ProductDetail> findByProductBarcodeAndUserUsername(String barcode, String username);

    void deleteByProductBarcodeAndUserUsername(String barcode, String name);
}
