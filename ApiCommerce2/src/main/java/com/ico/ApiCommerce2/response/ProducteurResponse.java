package com.ico.ApiCommerce2.response;

import com.ico.ApiCommerce2.enumeration.CategorieProducteur;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProducteurResponse {
    private String email;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private CategorieProducteur categorie;
}
