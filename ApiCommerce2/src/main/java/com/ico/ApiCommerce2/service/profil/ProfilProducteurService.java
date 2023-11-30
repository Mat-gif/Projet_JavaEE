package com.ico.ApiCommerce2.service.profil;

import com.ico.ApiCommerce2.component.ResponseUtil;
import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.repository.ClientRepository;
import com.ico.ApiCommerce2.repository.ProducteurRepository;
import com.ico.ApiCommerce2.request.ProfilClientRequest;
import com.ico.ApiCommerce2.request.ProfilProducteurRequest;
import com.ico.ApiCommerce2.response.ClientResponse;
import com.ico.ApiCommerce2.response.ProducteurResponse;
import com.ico.ApiCommerce2.response.ProduitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfilProducteurService {

    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    @Autowired
    private final ResponseUtil response;
    private final ProducteurRepository producteurRepository;

    @Transactional
    public ProducteurResponse updateProducteur(ProfilProducteurRequest request) {
        int updatedRows = producteurRepository.updateProducteurByEmail(
                userDetailsUtil.getEmail(),
                request.getNom(),
                request.getPrenom(),
                request.getAdresse(),
                request.getTelephone()
        );

        if (updatedRows > 0) {
            producteurRepository.flush(); // Forcer la synchronisation avec la base de données
            Producteur producteur = producteurRepository.findByEmail(userDetailsUtil.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Client non trouvé"));
            return response.producteurResponse(producteur);
        } else {
            throw new RuntimeException("La mise à jour a échoué pour l'utilisateur avec l'e-mail: " + userDetailsUtil.getEmail());
        }
    }

    public ProducteurResponse afficherProducteur() {
        Producteur producteur = producteurRepository.findByEmail(userDetailsUtil.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Client non trouvé"));
        return response.producteurResponse(producteur);


    }
}
