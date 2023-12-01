package com.ico.ApiCommerce2.controller.profil;

import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.controller.produit.ProduitProducteurController;
import com.ico.ApiCommerce2.request.ProfilClientRequest;
import com.ico.ApiCommerce2.service.profil.ProfilClientService;
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
@RequestMapping("/api/client/profil")
@PreAuthorize("hasRole('CLIENT')")
public class ProfilClientController {
    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    private static final Logger logger = LoggerFactory.getLogger(ProfilClientController.class);
    private final ProfilClientService service;

    @PutMapping()
    public ResponseEntity updateProfile(@Valid @RequestBody ProfilClientRequest request)
    {
        logger.info("PUT::/api/client/profil : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.updateClient(request), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity afficherClient()
    {
        logger.info("GET::/api/client/profil {}",userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.afficherClient(), HttpStatus.OK);
    }

}
