package com.ico.ApiCommerce2.controller.profil;

import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.controller.produit.ProduitProducteurController;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.request.ProfilRequest;
import com.ico.ApiCommerce2.response.ClientResponse;
import com.ico.ApiCommerce2.response.ProducteurResponse;
import com.ico.ApiCommerce2.response.ProduitResponse;
import com.ico.ApiCommerce2.service.produit.ProduitService;
import com.ico.ApiCommerce2.service.profil.ProfilService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfilController {
    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    private static final Logger logger = LoggerFactory.getLogger(ProfilController.class);
    private final ProfilService service;

    @GetMapping ("/profil/producteur/{id}")
    public ResponseEntity afficherProducteur(
            @PathVariable  String id
    )
    {
        logger.info("/api/profil/producteur/{}  : {}", id,userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.afficherProducteur(id), HttpStatus.OK);
    }


    @GetMapping ("/profil/client/{id}")
    public ResponseEntity afficherClient(
            @PathVariable String id
    )
    {
        logger.info("/api/profil/client/{}  : {}", id,userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.afficherClient(id), HttpStatus.OK);
    }

}
