package com.zancheema.pantry.product;

import com.zancheema.pantry.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @EmbeddedId
    private ProductId productId;

    @Column(nullable = false)
    private int quantity;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductId implements Serializable {
        private String barcode;
        @ManyToOne(optional = false)
        private User user;
    }
}
