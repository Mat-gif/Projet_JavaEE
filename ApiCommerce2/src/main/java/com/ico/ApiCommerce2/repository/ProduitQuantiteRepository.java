package com.ico.ApiCommerce2.repository;

import com.ico.ApiCommerce2.entity.ProduitQuantite;
import com.ico.ApiCommerce2.enumeration.StatusProduitQuantite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProduitQuantiteRepository extends JpaRepository<ProduitQuantite, Long> {

    @Modifying
    @Query("UPDATE ProduitQuantite pq SET pq.statusProduitQuantite = :status WHERE pq.id = :id")
    void updateStatusProduitQuantite(Long id, StatusProduitQuantite status);

    @Modifying
    @Query("UPDATE Produit p SET p.quantite =p.quantite - :quantite WHERE p = (SELECT pq.produit FROM ProduitQuantite pq WHERE pq.id = :id)")
    void updateProduitQuantite(@Param("id") Long id, @Param("quantite") int quantite);

}

