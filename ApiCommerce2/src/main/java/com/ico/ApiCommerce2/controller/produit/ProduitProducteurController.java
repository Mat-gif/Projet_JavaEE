package com.ico.ApiCommerce2.controller.produit;


import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.controller.commande.CommandeProducteurController;
import com.ico.ApiCommerce2.exception.ProductNotFoundException;
import com.ico.ApiCommerce2.exception.ProfilNotFoundException;
import com.ico.ApiCommerce2.request.ProduitRequest;
import com.ico.ApiCommerce2.response.SuccessResponse;
import com.ico.ApiCommerce2.service.produit.ProduitService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/producteur/produit")

public class ProduitProducteurController {
    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    private static final Logger logger = LoggerFactory.getLogger(ProduitProducteurController.class);
    private final ProduitService produitService;


    @GetMapping()
    public ResponseEntity afficherMesProduits() throws ProfilNotFoundException, ProductNotFoundException {
        logger.info("GET::/api/producteur/produit : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(produitService.afficherProduits(), HttpStatus.OK);
    }

    /**
     * Ajoute un nouveau produit à la base de données.
     *
     * @param produitRequest Les informations du produit à ajouter.
     * @return Une ResponseEntity contenant le ProduitResponse résultant de l'ajout.
     */
    @PostMapping()
    public ResponseEntity ajouterProduit(
            @Valid @RequestBody ProduitRequest produitRequest
    )
    {
        logger.info("POST::/api/producteur/produit : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(produitService.ajouter(produitRequest), HttpStatus.OK);
    }

    /**
     * Modifie un produit existant dans la base de données.
     *
     * @param produitRequest Les informations du produit à mettre à jour.
     * @return Une ResponseEntity contenant le ProduitResponse résultant de la modification.
     */
    @PutMapping()
    public ResponseEntity modifierProduit(
            @Valid @RequestBody ProduitRequest produitRequest
    ) throws ProductNotFoundException, ProfilNotFoundException
    {
        logger.info("PUT::/api/producteur/produit : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(produitService.modifier(produitRequest), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity supprimerProduit(
            @PathVariable @Pattern(regexp = "^[0-9]+$", message = "L'ID doit être un nombre.") String id
    ) throws ProductNotFoundException, ProfilNotFoundException
    {
        logger.info("DELETE::/api/producteur/produit : {}, {}", userDetailsUtil.getEmail(), id);
        return new ResponseEntity<>(produitService.supprimer(Long.parseLong(id)), HttpStatus.OK);
    }


}
