package com.ico.ApiCommerce2.service.profil;

import com.ico.ApiCommerce2.component.ResponseUtil;
import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.repository.ClientRepository;
import com.ico.ApiCommerce2.repository.ProducteurRepository;
import com.ico.ApiCommerce2.response.ClientResponse;
import com.ico.ApiCommerce2.response.ProducteurResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfilService {

    @Autowired
    private final ResponseUtil response;

    private final ProducteurRepository producteurRepository;
    private final ClientRepository clientRepository;


    public ClientResponse afficherClient(String email) {
        Client client = clientRepository.findByEmail(email).get();
        return response.clientResponse(client);
    }

    public ProducteurResponse afficherProducteur(String email) {
        Producteur producteur = producteurRepository.findByEmail(email).get();
        return response.producteurResponse(producteur);

    }
}
