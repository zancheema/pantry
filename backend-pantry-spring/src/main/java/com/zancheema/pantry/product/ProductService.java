package com.zancheema.pantry.product;

import com.zancheema.pantry.product.dto.*;

import java.security.Principal;
import java.util.Optional;

public interface ProductService {
    Products getProducts(Principal principal);

    Optional<ProductInfo> getProduct(Principal principal, String barcode);

    ExistsInfo productExists(Principal principal, String barcode);

    Optional<ProductInfo> createProduct(Principal principal, CreateProductPayload payload);

    Optional<ProductInfo> addProduct(Principal principal, String barcode, AddProductPayload payload);

    Optional<ProductInfo> useProduct(Principal principal, String barcode, UseProductPayload payload);

    void useProductCompletely(String barcode);
}
