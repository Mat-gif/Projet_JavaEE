package com.ico.ApiCommerce2.repository;

import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.entity.Produit;
import com.ico.ApiCommerce2.enumeration.CategorieProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> findByIdAndProducteur(Long id, Producteur producteur);
    Optional<List<Produit>> findByProducteur(Producteur producteur);

    @Query("SELECT p FROM Produit p WHERE p.id = :id AND p.quantite > :quantite")
    Optional<Produit>  findProductByIdAndQuantityGreaterThan(@Param("id") Long id, @Param("quantite")  int quantite);


    @Modifying
    @Transactional
    @Query("UPDATE Produit p SET p.nom = :produitRequestNom, p.description = :description, p.quantite = :quantite, p.prix = :prix WHERE p.id = :id")
    int updateMyProduit(@Param("produitRequestNom") String produitRequestNom, @Param("description") String description, @Param("quantite") int quantite, @Param("prix") Double prix, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Produit p SET p.isDelete = true WHERE p.id = :id")
    int deleteMyProduit( @Param("id") Long id);






    @Query("SELECT p FROM Produit p WHERE p.categorie = :categorie")
    Optional<List<Produit>> findByCategorie(@Param("categorie") CategorieProduit categorie);}
