package com.ico.ApiCommerce2.service.produit;

import com.ico.ApiCommerce2.component.ResponseUtil;
import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.entity.Produit;
import com.ico.ApiCommerce2.enumeration.CategorieProduit;
import com.ico.ApiCommerce2.exception.ProductNotFoundException;
import com.ico.ApiCommerce2.exception.ProfilNotFoundException;
import com.ico.ApiCommerce2.repository.ProducteurRepository;
import com.ico.ApiCommerce2.repository.ProduitRepository;
import com.ico.ApiCommerce2.request.ProduitRequest;
import com.ico.ApiCommerce2.response.ProduitResponse;
import com.ico.ApiCommerce2.response.ProduitsResponse;
import com.ico.ApiCommerce2.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitService {
    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    @Autowired
    private final ResponseUtil response;
    private final ProduitRepository produitRepository;
    private final ProducteurRepository producteurRepository;


    public ProduitResponse ajouter(ProduitRequest produitRequest)
    {
        // J'jaoute un produit
        Produit  produit = producteurRepository.ajoutProduit(produitRequest,userDetailsUtil.getUserDetails());
        // Je retourne la réponse
        return response.produitResponse(produit);
    }

    public ProduitResponse afficher(String id) throws ProductNotFoundException {
        return response.produitResponse(produitRepository
                .findById(Long.parseLong(id))
                .orElseThrow(()-> new ProductNotFoundException("Product not found")));
    }

    @Transactional
        public ProduitsResponse afficherCategorie(CategorieProduit categorie) throws ProductNotFoundException {
        List<Produit> produitsParCategorie = produitRepository
                .findByCategorie(categorie)
                .orElseThrow(() -> new ProductNotFoundException("Aucun produit trouvé pour cette catégorie"));

        return response.produitsResponse(produitsParCategorie);
    }


    public ProduitResponse modifier(ProduitRequest produitRequest) throws ProductNotFoundException, ProfilNotFoundException {
//        Produit produit = producteurRepository
//                .findProduitByIdAndProducteurEmail(produitRequest.getId(),userDetailsUtil.getEmail())
//                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
//        // Je met a jour mon Produit
//        produit.setDescription(produitRequest.getDescription());
//        produit.setNom(produitRequest.getNom());
//        produit.setPrix(produitRequest.getPrix());
//        produit.setQuantite(produitRequest.getQuantite());
//        produit.setCategorie(produitRequest.getCategorie());
//        Producteur producteur = producteurRepository
//                .findByEmail(userDetailsUtil.getEmail())
//                .orElseThrow(() -> new ProfilNotFoundException("Profil not found"));
//
//        producteur.getProduits().add(produit);
//        producteurRepository.save(producteur);

        produitRepository.updateMyProduit(produitRequest.getNom(), produitRequest.getDescription(), produitRequest.getQuantite(), produitRequest.getPrix(), produitRequest.getId());
        Produit produit = producteurRepository
                .findProduitByIdAndProducteurEmail(produitRequest.getId(),userDetailsUtil.getEmail())
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        return response.produitResponse(produit);
    }


    public SuccessResponse supprimer(Long id) throws ProductNotFoundException, ProfilNotFoundException {
        Produit produit = producteurRepository
                .findProduitByIdAndProducteurEmail(id,userDetailsUtil.getEmail())
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        Producteur producteur = producteurRepository
                .findByEmail(userDetailsUtil.getEmail())
                .orElseThrow(() -> new ProfilNotFoundException("Profil not found"));
        // Je met a jour la liste de produit
        producteur.getProduits().remove(produit);
        producteurRepository.save(producteur);
        produitRepository.delete(produit);
        return null;
    }


    public ProduitsResponse afficherProduits() throws ProfilNotFoundException, ProductNotFoundException {
        Producteur producteur = producteurRepository
                .findByEmail(userDetailsUtil.getEmail())
                .orElseThrow(() -> new ProfilNotFoundException("Profil not found"));
        List<Produit> produits = produitRepository.findByProducteur(producteur).orElseThrow(() -> new ProductNotFoundException("Pas de produits") );
        return response.produitsResponse(produits);
    }

    public ProduitsResponse getAllProducts() throws ProductNotFoundException {
        List<Produit> produits = produitRepository.findAll();

        if (produits.isEmpty()) {
            throw new ProductNotFoundException("Aucun produit trouvé");
        }

        return response.produitsResponse(produits);
    }

}
