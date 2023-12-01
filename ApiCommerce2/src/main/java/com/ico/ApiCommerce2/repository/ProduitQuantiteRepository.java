package com.ico.ApiCommerce2.repository;

import com.ico.ApiCommerce2.entity.ProduitQuantite;
import com.ico.ApiCommerce2.enumeration.StatusProduitQuantite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProduitQuantiteRepository extends JpaRepository<ProduitQuantite, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ProduitQuantite pq SET pq.statusProduitQuantite = :status WHERE pq.id = :id")
    void updateStatusProduitQuantite(Long id, StatusProduitQuantite status);

    @Modifying
    @Transactional
    @Query("UPDATE Produit p SET p.quantite =p.quantite - :quantite WHERE p = (SELECT pq.produit FROM ProduitQuantite pq WHERE pq.id = :id)")
    void updateProduitQuantite(@Param("id") Long id, @Param("quantite") int quantite);

}

