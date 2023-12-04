package com.ico.ApiCommerce2.controller.produit;


import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.controller.commande.CommandeProducteurController;
import com.ico.ApiCommerce2.entity.Produit;
import com.ico.ApiCommerce2.enumeration.CategorieProduit;
import com.ico.ApiCommerce2.exception.ProductNotFoundException;
import com.ico.ApiCommerce2.response.ProduitResponse;
import com.ico.ApiCommerce2.service.produit.ProduitService;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client/produit")
public class ProduitController {    @Autowired
private final UserDetailsUtil userDetailsUtil;

    private static final Logger logger = LoggerFactory.getLogger(ProduitController.class);
    private final ProduitService produitService;

    /**
     * Récupère un produit par son ID.
     *
     * @param id L'identifiant du produit à récupérer.
     * @return Une ResponseEntity contenant le ProduitResponse correspondant au produit trouvé.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity ResponseEntityafficherProduit(
            @PathVariable @Pattern(regexp = "^[0-9]+$", message = "L'ID doit être un nombre.") String id
    ) throws ProductNotFoundException {
        logger.info("/api/client/produit/id/{}  : {}", id,userDetailsUtil.getEmail());
        return new ResponseEntity<>(produitService.afficher(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() throws ProductNotFoundException {
        logger.info("/api/client/produit/all : {}", userDetailsUtil.getEmail());
        return new ResponseEntity<>(produitService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/categorie/{categorieStr}")
    public ResponseEntity<?> ResponseEntityafficherProduitCategorie(
            @PathVariable String categorieStr) throws ProductNotFoundException {
        try {
            CategorieProduit categorie = CategorieProduit.valueOf(categorieStr.toUpperCase());
            logger.info("/api/client/produit/categorie/{} : {}", categorie, userDetailsUtil.getEmail());
            return new ResponseEntity<>(produitService.afficherCategorie(categorie), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            // Gérer les cas où la chaîne n'est pas une valeur valide de l'enum
            return new ResponseEntity<>("Catégorie non valide", HttpStatus.BAD_REQUEST);
        }
    }



}
