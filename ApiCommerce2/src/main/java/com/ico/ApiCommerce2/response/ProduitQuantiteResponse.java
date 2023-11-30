package com.ico.ApiCommerce2.response;

import com.ico.ApiCommerce2.enumeration.StatusProduitQuantite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProduitQuantiteResponse {
    private Long id;
    private int quantite;
    private StatusProduitQuantite statusProduitQuantite;
}