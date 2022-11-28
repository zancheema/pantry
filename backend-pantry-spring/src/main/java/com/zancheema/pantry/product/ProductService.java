package com.zancheema.pantry.product;

import com.zancheema.pantry.product.dto.*;

import java.security.Principal;
import java.util.Optional;

public interface ProductService {
    Products getProducts(Principal principal);

    Optional<ProductInfo> getProduct(Principal principal, String barcode);

    ExistsInfo productExists(Principal principal, String barcode);

    ProductInfo addProduct(Principal principal, AddProductPayload payload);

    Optional<ProductInfo> useProduct(Principal principal, String barcode, UseProductPayload payload);

    void useProductCompletely(Principal principal, String barcode);
}
