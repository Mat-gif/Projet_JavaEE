package com.ico.ApiCommerce2.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfilClientRequest {

    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String adresse;
    @NotBlank
    private String telephone;

}
