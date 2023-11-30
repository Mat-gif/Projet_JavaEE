package com.ico.ApiCommerce2.controller.profil;

import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.controller.produit.ProduitProducteurController;
import com.ico.ApiCommerce2.request.ProfilClientRequest;
import com.ico.ApiCommerce2.request.ProfilProducteurRequest;
import com.ico.ApiCommerce2.service.profil.ProfilProducteurService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/producteur/profil")
public class ProfilProducteurController {
    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    private static final Logger logger = LoggerFactory.getLogger(ProfilProducteurController.class);
    private final ProfilProducteurService service;

    @PutMapping()
    public ResponseEntity updateProfile(@Valid @RequestBody ProfilProducteurRequest request)
    {
        logger.info("PUT::/api/producteur/profil : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.updateProducteur(request), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity afficherProducteur()
    {
        logger.info("GET::/api/producteur/profil {}",userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.afficherProducteur(), HttpStatus.OK);
    }

}
