package com.ico.ApiCommerce2.request;



import com.ico.ApiCommerce2.enumeration.StatusCommande;
import com.ico.ApiCommerce2.response.ProduitQuantiteResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeRequest {
    @NotBlank
    private Long id;
    @NotBlank
    private StatusCommande status;
    @NotBlank
    private String client_id;
    @NotBlank
    private List<ProduitQuantiteRequest> produits ;
    @NotBlank
    private Date date;
}