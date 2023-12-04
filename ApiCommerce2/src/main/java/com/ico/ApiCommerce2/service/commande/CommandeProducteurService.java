package com.ico.ApiCommerce2.service.commande;

import com.ico.ApiCommerce2.component.ResponseUtil;
import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.entity.Commande;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.entity.ProduitQuantite;
import com.ico.ApiCommerce2.enumeration.StatusCommande;
import com.ico.ApiCommerce2.enumeration.StatusProduitQuantite;
import com.ico.ApiCommerce2.exception.CommandeNotFoundException;
import com.ico.ApiCommerce2.exception.ProfilExistException;
import com.ico.ApiCommerce2.exception.ProfilNotFoundException;
import com.ico.ApiCommerce2.repository.ClientRepository;
import com.ico.ApiCommerce2.repository.CommandeRepository;
import com.ico.ApiCommerce2.repository.ProducteurRepository;
import com.ico.ApiCommerce2.repository.ProduitQuantiteRepository;
import com.ico.ApiCommerce2.request.CommandeRequest;
import com.ico.ApiCommerce2.request.CommandesRequest;
import com.ico.ApiCommerce2.request.ProduitQuantiteRequest;
import com.ico.ApiCommerce2.request.StatutCommandeRequest;
import com.ico.ApiCommerce2.response.CommandeResponse;
import com.ico.ApiCommerce2.response.CommandesResponse;
import com.ico.ApiCommerce2.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandeProducteurService {

    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    @Autowired
    private final ResponseUtil response;
    private final CommandeRepository commandeRepository;
    private final ProducteurRepository producteurRepository;
    private final ProduitQuantiteRepository produitQuantiteRepository;
    private final NotificationService notificationService;
    private final ClientRepository clientRepository;
    public Object afficher() throws ProfilNotFoundException {
        Producteur producteur = producteurRepository
                .findByEmail(userDetailsUtil.getEmail())
                .orElseThrow(()-> new ProfilNotFoundException("Profil not found"));
        List<Commande> commandes = commandeRepository.findCommandesByProducteur(producteur);
        return response.commandesResponse(commandes);
    }
    public Object afficher(String id) throws CommandeNotFoundException {
        Commande commande = commandeRepository
                .findById(Long.valueOf(id))
                .orElseThrow(()-> new CommandeNotFoundException("Command not found"));
        return null;
    }

    @Transactional
    public String changerStatus(CommandeRequest request) throws ProfilNotFoundException {
        Client client = clientRepository.findByEmail(request.getClient_id()).orElseThrow(()->new ProfilNotFoundException("not found"));


        notificationService.sendNotification(client.getToken(),"Commande de "+ client.getNom());

        int updatedRows = commandeRepository.updateStatusCommande(request.getId(), request.getStatus(), userDetailsUtil.getEmail());
        if (updatedRows > 0) {
            commandeRepository.flush();
        } else {
            throw new RuntimeException("La mise à jour a échoué pour la commande : " + request.getId());
        }
        if(request.getStatus() != StatusCommande.REFUS)
        {
            for (ProduitQuantiteRequest produitQuantite : request.getProduits())
            {
                switch (produitQuantite.getStatusProduitQuantite()){
                    case ACCEPT -> {
                        produitQuantiteRepository.updateProduitQuantite(produitQuantite.getId(), produitQuantite.getQuantite());
                    }
                    case REFUSE -> {
                        produitQuantiteRepository.updateStatusProduitQuantite(produitQuantite.getId(), produitQuantite.getStatusProduitQuantite());
                    }
                }
            }
            produitQuantiteRepository.flush();
        }

        return "ok";
    }


}
