package com.ico.ApiCommerce2.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProduitResponse {
    private Long id;
    private String emailProducteur;
    private String nom;
    private double prix;
    private String description;
    private int quantite;
    private Date date_publication;

}
