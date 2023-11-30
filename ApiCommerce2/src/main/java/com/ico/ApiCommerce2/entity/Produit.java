package com.ico.ApiCommerce2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="produit")
public class Produit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="producteur_id" )
    private Producteur producteur;


    @Column(name = "nom", updatable = true, nullable = false)

    private String nom;


    @Column(name = "prix", updatable = true, nullable = false)

    private double prix;


    @Column(name = "description", updatable = true, nullable = true)

    private String description;


    @Column(name = "quantite", updatable = true, nullable = true)

    private int quantite;

    @Column(name = "date_publication", updatable = false, nullable = false)

    private Date date_publication;


    public void setId(Long id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
