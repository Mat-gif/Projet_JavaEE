package com.ico.ApiCommerce2.request;

import com.ico.ApiCommerce2.enumeration.StatusProduitQuantite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProduitQuantiteRequest {
    @NotBlank
    private Long id;
    @NotBlank
    private int quantite;
    @NotBlank
    private StatusProduitQuantite statusProduitQuantite ;

}