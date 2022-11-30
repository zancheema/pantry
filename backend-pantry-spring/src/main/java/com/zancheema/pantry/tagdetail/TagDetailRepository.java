package com.zancheema.pantry.tagdetail;

import com.zancheema.pantry.productdetail.ProductDetail;
import com.zancheema.pantry.tag.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TagDetailRepository extends CrudRepository<TagDetail, Long> {
    Set<TagDetail> findAllByProductDetail(ProductDetail detail);

    boolean existsByTagAndProductDetail(Tag tag, ProductDetail productDetail);
}
