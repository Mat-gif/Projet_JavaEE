package com.ico.ApiCommerce2.component;

import com.ico.ApiCommerce2.entity.*;
import com.ico.ApiCommerce2.response.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseUtil {

    public ProducteurResponse producteurResponse(Producteur producteur)
    {
        return ProducteurResponse.builder()
                .email(producteur.getEmail())
                .nom(producteur.getNom())
                .prenom(producteur.getPrenom())
                .adresse(producteur.getAdresse())
                .telephone(producteur.getTelephone())
                .categorie(producteur.getCategorie())
                .build();
    }



    public ClientResponse clientResponse(Client client)
    {
        return ClientResponse
                .builder()
                .email(client.getEmail())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .adresse(client.getAdresse())
                .telephone(client.getTelephone())
                .build();
    }


    public ProduitResponse produitResponse(Produit produit)
    {
        return ProduitResponse.builder()
                .id(produit.getId())
                .nom(produit.getNom())
                .prix(produit.getPrix())
                .quantite(produit.getQuantite())
                .description(produit.getDescription())
                .date_publication( Date.valueOf(LocalDate.now()))
                .emailProducteur(produit.getProducteur().getEmail())
                .categorie(produit.getCategorie())
                .build();
    }
    public  CommandeResponse commandeResponse(Commande commande) {
        return CommandeResponse
                .builder()
                .id(commande.getId())
                .status(commande.getStatus())
                .client_id(commande.getClient().getEmail())
                .date(commande.getDate())
                .build();
    }

    public CommandesResponse commandesResponse( List<Commande> commandes)
    {
        CommandesResponse commandesResponse = new CommandesResponse();
        commandes.forEach(c->{
            List<ProduitQuantiteResponse> produitQuantiteResponses = new ArrayList<>();
            c.getProduits().forEach(p ->{
                ProduitQuantiteResponse produitQuantiteResponse = new ProduitQuantiteResponse();
                produitQuantiteResponse.setQuantite(p.getQuantite());
                produitQuantiteResponse.setId(p.getId());
                produitQuantiteResponses.add(produitQuantiteResponse);
                produitQuantiteResponse.setStatusProduitQuantite(p.getStatusProduitQuantite());
            });


            commandesResponse.getCommandes().add(CommandeResponse
                    .builder()
                    .id(c.getId())
                    .status(c.getStatus())
                    .client_id(c.getClient().getEmail())
                    .produits(produitQuantiteResponses)
                    .date(c.getDate())
                    .build());
        });


        return commandesResponse;
    }

    public ProduitsResponse produitsResponse(List<Produit> produits) {
        ProduitsResponse produitsResponse = ProduitsResponse.builder().produits(new ArrayList<>()).build();

        for (Produit produit : produits)
        {
            ProduitResponse produitResponse = ProduitResponse.builder()
                                                            .emailProducteur(produit.getProducteur().getEmail())
                                                            .nom(produit.getNom())
                                                            .description(produit.getDescription())
                                                            .quantite(produit.getQuantite())
                                                            .id(produit.getId())
                                                            .prix(produit.getPrix())
                                                            .categorie(produit.getCategorie())
                                                            .build();
            produitsResponse.getProduits().add(produitResponse);

        }



        return produitsResponse;

    }

    public AuthenticationProducteurResponse authenticationProducteurResponse(String jwtToken, Producteur profil) {

        ProducteurResponse producteurResponse = ProducteurResponse
                .builder()
                .categorie(profil.getCategorie())
                .telephone(profil.getTelephone())
                .adresse(profil.getAdresse())
                .email(profil.getEmail())
                .nom(profil.getNom())
                .prenom(profil.getPrenom())
                .build();

        return AuthenticationProducteurResponse.builder().token(jwtToken).producteur(producteurResponse).build();
    }
}
