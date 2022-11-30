package com.zancheema.pantry.product;

import com.zancheema.pantry.common.StreamUtils;
import com.zancheema.pantry.product.dto.*;
import com.zancheema.pantry.productdetail.ProductDetail;
import com.zancheema.pantry.productdetail.ProductDetailRepository;
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
    private final ProductDetailRepository productDetailRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public Products getProducts(Principal principal) {
        User user = getUser(principal);
        Set<ProductInfo> products = StreamUtils.stream(productDetailRepository.findAllByUser(user))
                .map(detail ->
                        new ProductInfo(detail.getProduct().getBarcode(), detail.getQuantity())
                )
                .collect(Collectors.toSet());
        return new Products(products);
    }

    @Override
    public Optional<ProductInfo> getProduct(Principal principal, String barcode) {
        return productDetailRepository
                .findByProductBarcodeAndUserUsername(barcode, principal.getName())
                .map(detail -> new ProductInfo(barcode, detail.getQuantity()));
    }

    @Override
    public ExistsInfo productExists(Principal principal, String barcode) {
        return productDetailRepository
                .findByProductBarcodeAndUserUsername(barcode, principal.getName())
                .map(productDetail -> new ExistsInfo(true, productDetail.getQuantity()))
                .orElseGet(() -> new ExistsInfo(false, 0));
    }

    @Override
    public ProductInfo addProduct(Principal principal, AddProductPayload payload) {
        User user = getUser(principal);
        Product product = productRepository.findById(payload.getBarcode())
                .orElse(productRepository.save(new Product(payload.getBarcode())));

        Optional<ProductDetail> optionalProductDetail = productDetailRepository
                .findByProductAndUser(product, user);
        ProductDetail productDetail;
        if (optionalProductDetail.isPresent()) {
            productDetail = optionalProductDetail.get();
            productDetail.setQuantity(productDetail.getQuantity() + payload.getQuantity());
        } else {
            productDetail = new ProductDetail(0, product, user, payload.getQuantity());
        }

        ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
        return new ProductInfo(product.getBarcode(), savedProductDetail.getQuantity());
    }

    @Override
    public Optional<ProductInfo> useProduct(Principal principal, String barcode, UseProductPayload payload) {
        Optional<ProductDetail> optionalProductDetail = productDetailRepository
                .findByProductBarcodeAndUserUsername(barcode, principal.getName());
        if (optionalProductDetail.isEmpty()) return Optional.empty();
        ProductDetail productDetail = optionalProductDetail.get();
        int newQuantity = productDetail.getQuantity() - payload.getQuantity();

        Optional<ProductInfo> productInfo;
        if (newQuantity < 0) {
            productInfo = Optional.empty();
        } else if (newQuantity > 0) {
            productDetail.setQuantity(newQuantity);
            ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
            productInfo = Optional.of(productMapper.toProductInfo(savedProductDetail));
        } else {
            productDetail.setQuantity(newQuantity);
            productDetailRepository.deleteByProductBarcodeAndUserUsername(barcode, principal.getName());
            productInfo = Optional.of(productMapper.toProductInfo(productDetail));
        }
        return productInfo;
    }

    @Override
    public void useProductCompletely(String barcode) {

    }

    private User getUser(Principal principal) {
        return userRepository.findById(principal.getName()).get();
    }
}
