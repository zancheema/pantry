package com.zancheema.pantry.product;

import com.zancheema.pantry.product.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Products getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<ProductInfo> getProduct(@PathVariable("barcode") String barcode) {
        Optional<ProductInfo> optionalProduct = productService.getProduct(barcode);
        return ResponseEntity.of(optionalProduct);
    }

    @GetMapping("/{barcode}/exists")
    public ExistsInfo productExists(@PathVariable("barcode") String barcode) {
        return productService.productExists(barcode);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductInfo> addProduct(@RequestBody @Valid AddProductPayload payload) throws URISyntaxException {
        ProductInfo product = productService.addProduct(payload);
        URI location = new URI("/api/products/" + product.barcode());
        return ResponseEntity.created(location)
                .body(product);
    }

    @PatchMapping("/{barcode}/use")
    public ResponseEntity<ProductInfo> useProduct(
            @PathVariable("barcode") String barcode,
            @RequestBody @Valid UseProductPayload payload
    ) {
        Optional<ProductInfo> optionalProduct = productService.useProduct(barcode, payload);
        if (optionalProduct.isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(optionalProduct.get());
    }

    @DeleteMapping("/{barcode}/use/all")
    public ResponseEntity<?> useProductCompletely(@PathVariable("barcode") String barcode) {
        productService.useProductCompletely(barcode);
        return ResponseEntity.noContent().build();
    }
}
