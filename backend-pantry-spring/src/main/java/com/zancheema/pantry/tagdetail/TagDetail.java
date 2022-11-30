package com.zancheema.pantry.tagdetail;

import com.zancheema.pantry.productdetail.ProductDetail;
import com.zancheema.pantry.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDetail {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    private Tag tag;

    @ManyToOne(optional = false)
    private ProductDetail productDetail;
}
