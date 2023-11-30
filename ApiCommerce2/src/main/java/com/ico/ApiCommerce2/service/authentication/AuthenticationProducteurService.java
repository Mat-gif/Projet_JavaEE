package com.ico.ApiCommerce2.service.authentication;

import com.ico.ApiCommerce2.component.ResponseUtil;
import com.ico.ApiCommerce2.exception.ProfilExistException;
import com.ico.ApiCommerce2.request.AuthenticationRequest;
import com.ico.ApiCommerce2.response.AuthenticationProducteurResponse;
import com.ico.ApiCommerce2.response.AuthenticationResponse;
import com.ico.ApiCommerce2.request.RegisterRequest;
import com.ico.ApiCommerce2.config.JwtService;
import com.ico.ApiCommerce2.entity.Producteur;
import com.ico.ApiCommerce2.enumeration.Role;
import com.ico.ApiCommerce2.repository.ProducteurRepository;
import com.ico.ApiCommerce2.repository.ProfilRepository;
import com.ico.ApiCommerce2.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationProducteurService implements AuthenticationService{

    private final ProfilRepository profilReposotory;
    private final ProducteurRepository producteurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final ResponseUtil response;


    @Override
    public AuthenticationResponse register(RegisterRequest request) throws ProfilExistException {
        // Si le profil producteur existe leve l'exception
        if(!this.profilNotExist(request)){throw new ProfilExistException("Email is already use");}
        // Je crée et j'ajoute un Producteur
        Producteur producteur = new Producteur();
        producteur.setEmail(request.getEmail());
        producteur.setPassword(passwordEncoder.encode(request.getPassword()));
        producteur.setRoles(Set.of(Role.PRODUCTEUR));
        Producteur nouveauProducteur = producteurRepository.save(producteur);
        // Je génère le token
        var jwtToken = jwtService.generateToken(nouveauProducteur);
        // je renvois la réponse
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    @Transactional
    public AuthenticationProducteurResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        producteurRepository.updateProducteurTokenByEmail(request.getEmail(), request.getTokenApp());
        // Je cherche le producteur
        var profil = producteurRepository.findByEmail(request.getEmail()).orElseThrow();


        // Je génère le token
        var jwtToken = jwtService.generateToken(profil);
        // je renvois la réponse
        return response.authenticationProducteurResponse(jwtToken, profil);
    }



    private Boolean profilNotExist(RegisterRequest request){
        return  profilReposotory.findByEmail(request.getEmail()).isEmpty();
    }

}
