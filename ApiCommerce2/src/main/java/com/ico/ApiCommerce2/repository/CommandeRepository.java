package com.ico.ApiCommerce2.repository;

import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.entity.Commande;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.enumeration.StatusCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {


    @Query("SELECT DISTINCT cq.commande FROM ProduitQuantite cq " +
            "INNER JOIN cq.produit p " +
            "INNER JOIN p.producteur pr " +
            "WHERE pr = :producteur")
    List<Commande> findCommandesByProducteur(@Param("producteur") Producteur producteur);


    @Query("SELECT DISTINCT cq.commande FROM ProduitQuantite cq " +
            "INNER JOIN cq.produit p " +
            "INNER JOIN p.producteur pr " +
            "WHERE cq.commande.client = :client")
    List<Commande> findCommandesByClient(@Param("client") Client client);



    @Modifying
    @Query("UPDATE Commande c " +
            "SET c.status = :status " +
            "WHERE c.id = :id " +
            "AND EXISTS (" +
            "   SELECT pq " +
            "   FROM ProduitQuantite pq " +
            "   INNER JOIN pq.produit p " +
            "   INNER JOIN p.producteur pr " +
            "   WHERE pq.commande = c " +
            "   AND pr.email = :email" +
            ")")
    int updateStatusCommande(@Param("id") Long id, @Param("status") StatusCommande status, @Param("email") String email);

}