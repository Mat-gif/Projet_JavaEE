package com.ico.ApiCommerce2.response;




import com.ico.ApiCommerce2.entity.Commande;

import com.ico.ApiCommerce2.entity.ProduitQuantite;
import com.ico.ApiCommerce2.enumeration.StatusCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import java.sql.Date;
import java.util.List;

import java.util.Date;
//import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeResponse {

    private Long id;

    private StatusCommande status;

    private String client_id;

    private  List<ProduitQuantiteResponse> produits ;


    private Date date;

}