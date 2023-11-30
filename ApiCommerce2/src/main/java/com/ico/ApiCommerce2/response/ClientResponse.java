package com.ico.ApiCommerce2.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private String email;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
}
