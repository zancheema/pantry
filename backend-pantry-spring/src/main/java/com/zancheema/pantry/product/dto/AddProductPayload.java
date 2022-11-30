package com.zancheema.pantry.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductPayload {
    private @NotBlank String barcode;
    private @NotNull @Min(1) Integer quantity;
    private Set<String> tags = new HashSet<>();
}
