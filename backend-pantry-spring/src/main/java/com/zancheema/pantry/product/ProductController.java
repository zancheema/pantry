package com.zancheema.pantry.product;

import com.zancheema.pantry.product.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Products getProducts(Principal principal) {
        return productService.getProducts(principal);
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<ProductInfo> getProduct(Principal principal, @PathVariable("barcode") String barcode) {
        Optional<ProductInfo> optionalProduct = productService.getProduct(principal, barcode);
        return ResponseEntity.of(optionalProduct);
    }

    @GetMapping("/{barcode}/exists")
    public ExistsInfo productExists(Principal principal, @PathVariable("barcode") String barcode) {
        return productService.productExists(principal, barcode);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductInfo> addProduct(
            Principal principal,
            @RequestBody @Valid AddProductPayload payload
    ) throws URISyntaxException {
        ProductInfo product = productService.addProduct(principal, payload);
        URI location = new URI("/api/products/" + product.barcode());
        return ResponseEntity.created(location)
                .body(product);
    }

    @PatchMapping("/{barcode}/use")
    public ResponseEntity<ProductInfo> useProduct(
            Principal principal,
            @PathVariable("barcode") String barcode,
            @RequestBody @Valid UseProductPayload payload
    ) {
        Optional<ProductInfo> optionalProduct = productService.useProduct(principal, barcode, payload);
        if (optionalProduct.isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(optionalProduct.get());
    }

    @DeleteMapping("/{barcode}/use/all")
    public ResponseEntity<?> useProductCompletely(@PathVariable("barcode") String barcode) {
        productService.useProductCompletely(barcode);
        return ResponseEntity.noContent().build();
    }
}
