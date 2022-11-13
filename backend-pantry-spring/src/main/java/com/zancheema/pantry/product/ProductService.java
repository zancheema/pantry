package com.zancheema.pantry.product;

import com.zancheema.pantry.product.dto.*;

import java.util.Optional;

public interface ProductService {
    Products getProducts();

    Optional<ProductInfo> getProduct(String barcode);

    ExistsInfo productExists(String barcode);

    ProductInfo addProduct(AddProductPayload payload);

    Optional<ProductInfo> useProduct(String barcode, UseProductPayload payload);

    void useProductCompletely(String barcode);
}
