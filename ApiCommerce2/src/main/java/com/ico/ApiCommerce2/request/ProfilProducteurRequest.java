package com.ico.ApiCommerce2.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfilProducteurRequest {

    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String adresse;
    @NotBlank
    private String telephone;

}
