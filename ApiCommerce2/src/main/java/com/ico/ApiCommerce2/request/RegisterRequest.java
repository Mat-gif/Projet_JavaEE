package com.ico.ApiCommerce2.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String nom;
    @NotBlank
    private String password;
}