package com.ico.ApiCommerce2.entity;

import com.ico.ApiCommerce2.enumeration.CategorieProducteur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "producteur")
public class Producteur extends Profil {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produit> produits;

    @Column(name = "categorie")
    private CategorieProducteur categorie;


}

