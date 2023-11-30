package com.ico.ApiCommerce2.repository;

import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.request.ProfilClientRequest;
import com.ico.ApiCommerce2.request.ProfilProducteurRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Client c SET c.nom = :nom, c.prenom = :prenom, c.adresse = :adresse, c.telephone = :telephone WHERE c.email = :email")
    int updateClientByEmail(String email, String nom, String prenom, String adresse, String telephone);

    @Modifying
    @Transactional
    @Query("UPDATE Client c SET  c.token = :tokenApp WHERE c.email = :email")
    int updateClientTokenByEmail(String email, String tokenApp);
}
