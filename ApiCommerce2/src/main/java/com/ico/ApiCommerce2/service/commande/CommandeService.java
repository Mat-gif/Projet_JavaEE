package com.ico.ApiCommerce2.service.commande;

import com.ico.ApiCommerce2.component.ResponseUtil;
import com.ico.ApiCommerce2.component.UserDetailsUtil;

import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.entity.Commande;
import com.ico.ApiCommerce2.entity.Produit;
import com.ico.ApiCommerce2.entity.ProduitQuantite;


import com.ico.ApiCommerce2.enumeration.StatusCommande;
import com.ico.ApiCommerce2.enumeration.StatusProduitQuantite;
import com.ico.ApiCommerce2.exception.ProductNotFoundException;
import com.ico.ApiCommerce2.exception.ProfilNotFoundException;
import com.ico.ApiCommerce2.repository.ClientRepository;
import com.ico.ApiCommerce2.repository.CommandeRepository;
import com.ico.ApiCommerce2.repository.ProduitRepository;
import com.ico.ApiCommerce2.request.CommandeRequest;
import com.ico.ApiCommerce2.request.ProduitQuantiteRequest;
import com.ico.ApiCommerce2.response.CommandeResponse;
import com.ico.ApiCommerce2.response.CommandesResponse;
import com.ico.ApiCommerce2.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommandeService {

    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    @Autowired
    private final ResponseUtil response;
    private final CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;
    private final ClientRepository clientRepository;
    private final NotificationService notificationService;

    //request qui choppe le user
    public CommandesResponse passerCommande(CommandeRequest request) throws ProductNotFoundException, ProfilNotFoundException {
        Client client = clientRepository.findByEmail(userDetailsUtil.getEmail())
                .orElseThrow(() -> new ProfilNotFoundException("Profil not found"));

        List<Commande> commandes = new ArrayList<>();
        HashMap<String, Commande> commandeHashMap = new HashMap<>();

        for (ProduitQuantiteRequest prod : request.getProduits()) {
            Produit produit = produitRepository.findProductByIdAndQuantityGreaterThan(prod.getId(), prod.getQuantite())
                    .orElseThrow(() -> new ProductNotFoundException("Quantite insuffisante"));

            String emailProducteur = produit.getProducteur().getEmail();
            Commande commande = commandeHashMap.get(emailProducteur);

            if (commande == null) {
                commande = Commande.builder()
                        .status(StatusCommande.EN_ATTENTE_DE_VALIDATION)
                        .date(Date.valueOf(java.time.LocalDate.now()))
                        .produits(new ArrayList<>())
                        .client(client)
                        .build();
                commandes.add(commande);
                commandeHashMap.put(emailProducteur, commande);
                notificationService.sendNotification(produit.getProducteur().getToken(),"Commande de "+ client.getNom());
            }

            ProduitQuantite produitQuantite = ProduitQuantite.builder()
                    .produit(produit)
                    .quantite(prod.getQuantite())
                    .commande(commande)
                    .statusProduitQuantite(StatusProduitQuantite.ACCEPT)
                    .build();

            commande.getListeProduit().add(produitQuantite);
        }

        // Sauvegarde des commandes et mise à jour du client
        commandeRepository.saveAll(commandes);

        // Mise à jour de la liste de commandes du client
        for (Commande commande : commandes) {
            client.getCommandes().add(commande);

        }
        clientRepository.save(client);

        return response.commandesResponse(commandes);
    }


    public CommandeResponse afficheCommande(String id)
    {
        return commandeResponse(afficheCommandeParId(Long.parseLong(id)));
    }

    private CommandeResponse commandeResponse(Commande commande) {
        return CommandeResponse
                .builder()
                .id(commande.getId())
                .status(commande.getStatus())
                .client_id(commande.getClient().getEmail())
                .date(commande.getDate())
                .build();
    }


    private Commande afficheCommandeParId(Long id)
    {
        Optional<Commande> commandeOptional = commandeRepository.findById(id);
        return commandeOptional.orElse(null);
    }

    public CommandesResponse afficheCommandes() throws ProfilNotFoundException {
        Client client = clientRepository.findByEmail(userDetailsUtil.getEmail())
            .orElseThrow(() -> new ProfilNotFoundException("Profil not found"));
        List<Commande> commandes = commandeRepository.findCommandesByClient(client);
        return response.commandesResponse(commandes);

    }
}