package com.ico.ApiCommerce2.repository;

import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.entity.Produit;
import com.ico.ApiCommerce2.enumeration.CategorieProducteur;
import com.ico.ApiCommerce2.enumeration.Role;
import com.ico.ApiCommerce2.request.ProduitRequest;
import com.ico.ApiCommerce2.request.ProfilClientRequest;
import com.ico.ApiCommerce2.request.ProfilProducteurRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface ProducteurRepository  extends JpaRepository<Producteur, String> {


    Optional<Producteur> findByEmail(String email);

    @Query("SELECT p FROM Producteur pr JOIN pr.produits p WHERE pr.email = :email AND p.id = :id")
    Optional<Produit> findProduitByIdAndProducteurEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE Producteur p SET p.nom = :nom, p.prenom = :prenom, p.categorie= :cat ,p.adresse = :adresse, p.telephone = :telephone WHERE p.email = :email")
    int updateProducteurByEmail(String email, String nom, String prenom, String adresse, String telephone, CategorieProducteur cat);

    @Modifying
    @Transactional
    @Query("UPDATE Producteur p SET  p.token = :tokenApp WHERE p.email = :email")
    int updateProducteurTokenByEmail(String email, String tokenApp);



    default Produit ajoutProduit(ProduitRequest produitRequest, UserDetails userDetails)
    {
        Producteur producteur = findByEmail(userDetails.getUsername()).get();
        Produit produit = Produit.builder()
                .nom(produitRequest.getNom())
                .prix(produitRequest.getPrix())
                .categorie(produitRequest.getCategorie())
                .quantite(produitRequest.getQuantite())
                .description(produitRequest.getDescription())
                .date_publication( Date.valueOf(LocalDate.now()))
                .producteur(producteur)
                .build();
        producteur.getProduits().add(produit);
        save(producteur);
        return produit;
    }



}
