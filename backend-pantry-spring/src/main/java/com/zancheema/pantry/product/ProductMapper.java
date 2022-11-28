package com.zancheema.pantry.product;

import com.zancheema.pantry.product.Product.ProductId;
import com.zancheema.pantry.product.dto.AddProductPayload;
import com.zancheema.pantry.product.dto.ProductInfo;
import com.zancheema.pantry.user.User;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(User user, AddProductPayload payload) {
        return new Product(
                new ProductId(payload.getBarcode(), user),
                payload.getQuantity()
        );
    }

    public ProductInfo toProductInfo(Product product) {
        return new ProductInfo(product.getProductId().getBarcode(), product.getQuantity());
    }
}
