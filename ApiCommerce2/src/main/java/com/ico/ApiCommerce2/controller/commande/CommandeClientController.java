package com.ico.ApiCommerce2.controller.commande;

import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.exception.ProductNotFoundException;
import com.ico.ApiCommerce2.exception.ProfilNotFoundException;
import com.ico.ApiCommerce2.request.CommandeRequest;

import com.ico.ApiCommerce2.service.commande.CommandeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class CommandeClientController {
    private static final Logger logger = LoggerFactory.getLogger(CommandeClientController.class);

    private final CommandeService commandeService;
    @Autowired
    private final UserDetailsUtil userDetailsUtil;

    @PostMapping("/commander")
    public ResponseEntity passerCommande(
            @Valid @RequestBody CommandeRequest commandeRequest
    ) throws  ProfilNotFoundException, ProductNotFoundException {
        logger.info("/api/client/commander : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(commandeService.passerCommande(commandeRequest), HttpStatus.OK);
    }

    @GetMapping("/commandes")
    public ResponseEntity afficherCommandes() throws ProfilNotFoundException
    {
        logger.info("/api/client/commandes : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(commandeService.afficheCommandes(),HttpStatus.OK);
    }


    @GetMapping("/commande/{id}")
    public ResponseEntity afficherCommande(
            @PathVariable @Pattern(regexp = "^[0-9]+$", message = "L'ID doit être un nombre.") String id
    )
    {
        logger.info("/api/client/commande/{}  : {}", id,userDetailsUtil.getEmail());
        return new ResponseEntity<>(commandeService.afficheCommande(id), HttpStatus.OK);
    }
}
