package com.zancheema.pantry.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupPayload {
    private @NotBlank String username;
    private @NotBlank String password;
}
