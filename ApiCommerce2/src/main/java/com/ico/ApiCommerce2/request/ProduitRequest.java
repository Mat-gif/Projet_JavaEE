package com.ico.ApiCommerce2.request;

import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.enumeration.CategorieProduit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProduitRequest {
    @NotBlank
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private double prix;

    @NotBlank
    private String description;

    @NotBlank
    private int quantite;

    @NotBlank
    private CategorieProduit categorie;
}
