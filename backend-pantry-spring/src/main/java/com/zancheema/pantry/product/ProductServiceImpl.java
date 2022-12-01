package com.zancheema.pantry.product;

import com.zancheema.pantry.common.StreamUtils;
import com.zancheema.pantry.product.dto.*;
import com.zancheema.pantry.productdetail.ProductDetail;
import com.zancheema.pantry.productdetail.ProductDetailRepository;
import com.zancheema.pantry.tag.TagRepository;
import com.zancheema.pantry.tagdetail.TagDetailRepository;
import com.zancheema.pantry.user.User;
import com.zancheema.pantry.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final TagRepository tagRepository;
    private final TagDetailRepository tagDetailRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository, ProductDetailRepository productDetailRepository, TagRepository tagRepository, TagDetailRepository tagDetailRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
        this.productDetailRepository = productDetailRepository;
        this.tagRepository = tagRepository;
        this.tagDetailRepository = tagDetailRepository;
    }

    @Override
    public Products getProducts(Principal principal) {
        User user = getUser(principal);
        Set<ProductInfo> products = StreamUtils.stream(productDetailRepository.findAllByUser(user))
                .map(this::getProductInfo)
                .collect(Collectors.toSet());
        return new Products(products);
    }

    @Override
    public Optional<ProductInfo> getProduct(Principal principal, String barcode) {
        return productDetailRepository
                .findByProductBarcodeAndUserUsername(barcode, principal.getName())
                .map(this::getProductInfo);
    }

    private ProductInfo getProductInfo(ProductDetail productDetail) {
        Set<String> tags = tagDetailRepository.findAllByProductDetail(productDetail)
                .stream()
                .map(tagDetail -> tagDetail.getTag().getName())
                .collect(Collectors.toSet());
        return new ProductInfo(
                productDetail.getProduct().getBarcode(),
                productDetail.getName(),
                productDetail.getQuantity(),
                tags
        );
    }

    @Override
    public ExistsInfo productExists(Principal principal, String barcode) {
        return productDetailRepository
                .findByProductBarcodeAndUserUsername(barcode, principal.getName())
                .map(productDetail -> new ExistsInfo(true, productDetail.getQuantity()))
                .orElseGet(() -> new ExistsInfo(false, 0));
    }

    @Override
    public Optional<ProductInfo> createProduct(Principal principal, CreateProductPayload payload) {
        if (productDetailRepository.existsByProductBarcodeAndUserUsername(
                payload.getBarcode(), principal.getName()
        )) {
            return Optional.empty();
        }

        User user = getUser(principal);
        Product product = productRepository.findById(payload.getBarcode())
                .orElse(productRepository.save(new Product(payload.getBarcode())));

        ProductDetail productDetail = new ProductDetail(0, payload.getName(), product, user, payload.getQuantity());

        ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
        Set<String> tags = tagDetailRepository.findAllByProductDetail(savedProductDetail)
                .stream()
                .map(detail -> detail.getTag().getName())
                .collect(Collectors.toSet());

        return Optional.of(
                new ProductInfo(
                        product.getBarcode(),
                        savedProductDetail.getName().trim(),
                        savedProductDetail.getQuantity(),
                        tags
                )
        );
    }

    @Override
    public Optional<ProductInfo> addProduct(Principal principal, String barcode, AddProductPayload payload) {
        ProductDetail productDetail = productDetailRepository
                .findByProductBarcodeAndUserUsername(barcode, principal.getName())
                .orElse(null);
        if (productDetail == null) {
            return Optional.empty();
        }
        productDetail.setQuantity(productDetail.getQuantity() + payload.getQuantity());

        ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
        Set<String> tags = tagDetailRepository.findAllByProductDetail(savedProductDetail)
                .stream()
                .map(detail -> detail.getTag().getName())
                .collect(Collectors.toSet());

        return Optional.of(
                new ProductInfo(
                        barcode,
                        savedProductDetail.getName(),
                        savedProductDetail.getQuantity(),
                        tags
                )
        );
    }

    @Transactional
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
