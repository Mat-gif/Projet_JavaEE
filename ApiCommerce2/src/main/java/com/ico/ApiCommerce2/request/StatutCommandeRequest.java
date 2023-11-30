package com.ico.ApiCommerce2.request;


import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.enumeration.StatusCommande;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class StatutCommandeRequest {
    @NotBlank
    private Long id;
    @NotBlank
    private StatusCommande status = StatusCommande.EN_ATTENTE_DE_VALIDATION ;
}
