package com.ico.ApiCommerce2.repository;

import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.entity.Produit;
import com.ico.ApiCommerce2.enumeration.CategorieProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> findByIdAndProducteur(Long id, Producteur producteur);
    Optional<List<Produit>> findByProducteur(Producteur producteur);

    @Query("SELECT p FROM Produit p WHERE p.id = :id AND p.quantite > :quantite")
    Optional<Produit>  findProductByIdAndQuantityGreaterThan(@Param("id") Long id, @Param("quantite")  int quantite);

    @Query("SELECT p FROM Produit p WHERE p.categorie = :categorie")
    Optional<List<Produit>> findByCategorie(@Param("categorie") CategorieProduit categorie);}
