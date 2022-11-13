package com.zancheema.pantry.product;

import com.zancheema.pantry.common.StreamUtils;
import com.zancheema.pantry.product.dto.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Products getProducts() {
        Set<ProductInfo> products = StreamUtils.stream(productRepository.findAll())
                .map(productMapper::toProductInfo)
                .collect(Collectors.toSet());
        return new Products(products);
    }

    @Override
    public Optional<ProductInfo> getProduct(String barcode) {
        return productRepository.findById(barcode)
                .map(productMapper::toProductInfo);
    }

    @Override
    public ExistsInfo productExists(String barcode) {
        boolean exists = productRepository.existsById(barcode);
        return productRepository.findById(barcode)
                .map(product -> new ExistsInfo(true, product.getQuantity()))
                .orElseGet(() -> new ExistsInfo(false, 0));
    }

    @Override
    public ProductInfo addProduct(AddProductPayload payload) {
        Optional<Product> optionalProduct = productRepository.findById(payload.getBarcode());
        Product product;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
            product.setQuantity(product.getQuantity() + payload.getQuantity());
        } else {
            product = productMapper.toProduct(payload);
        }
        productRepository.save(product);
        return productMapper.toProductInfo(product);
    }

    @Override
    public Optional<ProductInfo> useProduct(String barcode, UseProductPayload payload) {
        Optional<Product> optionalProduct = productRepository.findById(barcode);
        if (optionalProduct.isEmpty()) return Optional.empty();
        Product product = optionalProduct.get();
        int newQuantity = product.getQuantity() - payload.getQuantity();

        Optional<ProductInfo> productInfo;
        if (newQuantity < 0) {
            productInfo = Optional.empty();
        } else if (newQuantity > 0) {
            product.setQuantity(newQuantity);
            Product savedProduct = productRepository.save(product);
            productInfo = Optional.of(productMapper.toProductInfo(savedProduct));
        } else {
            product.setQuantity(newQuantity);
            productRepository.deleteById(barcode);
            productInfo = Optional.of(productMapper.toProductInfo(product));
        }
        return productInfo;
    }

    @Override
    public void useProductCompletely(String barcode) {

    }
}
