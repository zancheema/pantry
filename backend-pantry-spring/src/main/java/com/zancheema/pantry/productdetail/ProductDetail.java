package com.zancheema.pantry.productdetail;

import com.zancheema.pantry.product.Product;
import com.zancheema.pantry.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private int quantity;
}
