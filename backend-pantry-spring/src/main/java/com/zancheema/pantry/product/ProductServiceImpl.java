package com.zancheema.pantry.product;

import com.zancheema.pantry.common.StreamUtils;
import com.zancheema.pantry.product.Product.ProductId;
import com.zancheema.pantry.product.dto.*;
import com.zancheema.pantry.user.User;
import com.zancheema.pantry.user.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Products getProducts(Principal principal) {
        Set<ProductInfo> products = StreamUtils
                .stream(productRepository.findAllByProductIdUserUsername(principal.getName()))
                .map(productMapper::toProductInfo)
                .collect(Collectors.toSet());
        return new Products(products);
    }

    @Override
    public Optional<ProductInfo> getProduct(Principal principal, String barcode) {
        ProductId productId = getProductId(principal, barcode);
        return productRepository.findByProductId(productId)
                .map(productMapper::toProductInfo);
    }

    @Override
    public ExistsInfo productExists(Principal principal, String barcode) {
        ProductId productId = getProductId(principal, barcode);
        return productRepository.findByProductId(productId)
                .map(product -> new ExistsInfo(true, product.getQuantity()))
                .orElseGet(() -> new ExistsInfo(false, 0));
    }

    @Override
    public ProductInfo addProduct(Principal principal, AddProductPayload payload) {
        ProductId productId = getProductId(principal, payload.getBarcode());
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        Product product;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
            product.setQuantity(product.getQuantity() + payload.getQuantity());
        } else {
            User user = userRepository.findById(principal.getName()).get();
            product = productMapper.toProduct(user, payload);
        }
        productRepository.save(product);
        return productMapper.toProductInfo(product);
    }

    @Override
    public Optional<ProductInfo> useProduct(Principal principal, String barcode, UseProductPayload payload) {
        ProductId productId = getProductId(principal, barcode);
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
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
            productRepository.delete(product);
            productInfo = Optional.of(productMapper.toProductInfo(product));
        }
        return productInfo;
    }

    @Override
    public void useProductCompletely(Principal principal, String barcode) {

    }

    private ProductId getProductId(Principal principal, String barcode) {
        User user = getUser(principal);
        return new ProductId(barcode, user);
    }

    private User getUser(Principal principal) {
        return userRepository.findById(principal.getName()).get();
    }
}
