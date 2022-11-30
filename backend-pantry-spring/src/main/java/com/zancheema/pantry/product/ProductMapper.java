package com.zancheema.pantry.product;

import com.zancheema.pantry.product.dto.ProductInfo;
import com.zancheema.pantry.productdetail.ProductDetail;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductInfo toProductInfo(ProductDetail productDetail) {
        return new ProductInfo(
                productDetail.getProduct().getBarcode(),
                productDetail.getQuantity()
        );
    }
//    public Product toProduct(AddProductPayload payload) {
//        return new Product(payload.getBarcode());
//    }
//
//    public ProductInfo toProductInfo(Product product) {
//        return new ProductInfo(product.getBarcode(), product.getQuantity());
//    }
}
