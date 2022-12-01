package com.zancheema.pantry.product.dto;

import java.util.Set;

public record ProductInfo(String barcode, String name, int quantity, Set<String> tags) {
}
