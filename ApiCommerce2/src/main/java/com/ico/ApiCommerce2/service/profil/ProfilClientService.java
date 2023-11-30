package com.ico.ApiCommerce2.service.profil;

import com.ico.ApiCommerce2.component.ResponseUtil;
import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.repository.ClientRepository;
import com.ico.ApiCommerce2.request.ProfilClientRequest;
import com.ico.ApiCommerce2.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfilClientService {
    @Autowired
    private final UserDetailsUtil userDetailsUtil;
    @Autowired
    private final ResponseUtil response;
    private final ClientRepository clientRepository;

    @Transactional
    public ClientResponse updateClient(ProfilClientRequest request) {
        int updatedRows = clientRepository.updateClientByEmail(
                userDetailsUtil.getEmail(),
                request.getNom(),
                request.getPrenom(),
                request.getAdresse(),
                request.getTelephone()
        );

        if (updatedRows > 0) {
            clientRepository.flush(); // Forcer la synchronisation avec la base de données
            Client client = clientRepository.findByEmail(userDetailsUtil.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Client non trouvé"));
            return response.clientResponse(client);
        } else {
            throw new RuntimeException("La mise à jour a échoué pour l'utilisateur avec l'e-mail: " + userDetailsUtil.getEmail());
        }

    }

    public ClientResponse afficherClient() {
        Client client = clientRepository.findByEmail(userDetailsUtil.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Client non trouvé"));
        return response.clientResponse(client);

    }
}
