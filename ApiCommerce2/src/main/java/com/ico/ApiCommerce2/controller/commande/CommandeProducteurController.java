package com.ico.ApiCommerce2.controller.commande;

import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.exception.CommandeNotFoundException;
import com.ico.ApiCommerce2.exception.ProfilNotFoundException;
import com.ico.ApiCommerce2.request.CommandeRequest;
import com.ico.ApiCommerce2.service.commande.CommandeProducteurService;
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
@RequestMapping("/api/producteur/")
public class CommandeProducteurController {
    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    private static final Logger logger = LoggerFactory.getLogger(CommandeProducteurController.class);
    private final CommandeProducteurService service;

    @GetMapping("/commande/{id}")
    public ResponseEntity afficherCommande(
            @PathVariable @Pattern(regexp = "^[0-9]+$", message = "L'ID doit être un nombre.") String id
    ) throws CommandeNotFoundException
    {
        logger.info("/api/producteur/commande/{}  : {}", id,userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.afficher(id), HttpStatus.OK);
    }

    @GetMapping("/commandes")
    public ResponseEntity afficherCommandes() throws ProfilNotFoundException
    {
        logger.info("/api/producteur/commandes : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.afficher(), HttpStatus.OK);
    }

    @PutMapping("/commande")
    public ResponseEntity validerCommande(@Valid @RequestBody CommandeRequest request) throws ProfilNotFoundException {
        logger.info("/api/producteur/commande : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(service.changerStatus(request), HttpStatus.OK);
    }
}
