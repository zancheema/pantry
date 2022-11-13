package com.zancheema.pantry.product;

import com.zancheema.pantry.product.dto.AddProductPayload;
import com.zancheema.pantry.product.dto.ProductInfo;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(AddProductPayload payload) {
        return new Product(payload.getBarcode(), payload.getQuantity());
    }

    public ProductInfo toProductInfo(Product product) {
        return new ProductInfo(product.getBarcode(), product.getQuantity());
    }
}
