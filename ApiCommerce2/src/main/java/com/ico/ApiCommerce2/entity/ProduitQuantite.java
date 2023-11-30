package com.ico.ApiCommerce2.entity;

import com.ico.ApiCommerce2.enumeration.StatusProduitQuantite;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProduitQuantite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
    @JoinColumn(name = "quantite")
    private int quantite;

    private StatusProduitQuantite statusProduitQuantite;
    public void setId(Long id) {
        this.id = id;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}

