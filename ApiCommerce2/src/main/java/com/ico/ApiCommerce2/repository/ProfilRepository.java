package com.ico.ApiCommerce2.repository;

import com.ico.ApiCommerce2.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilRepository extends JpaRepository<Profil, String> {

    Optional<Profil> findByEmail(String email);
//    Optional<Profil> findByEmailClient(String email, boolean estClient);
}
