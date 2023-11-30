package com.ico.ApiCommerce2.service.authentication;

import com.ico.ApiCommerce2.component.UserDetailsUtil;
import com.ico.ApiCommerce2.exception.ProfilExistException;
import com.ico.ApiCommerce2.request.AuthenticationRequest;
import com.ico.ApiCommerce2.response.AuthenticationResponse;
import com.ico.ApiCommerce2.request.RegisterRequest;
import com.ico.ApiCommerce2.config.JwtService;
import com.ico.ApiCommerce2.entity.Client;
import com.ico.ApiCommerce2.entity.Profil;
import com.ico.ApiCommerce2.repository.ClientRepository;
import com.ico.ApiCommerce2.repository.ProfilRepository;
import com.ico.ApiCommerce2.enumeration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationClientService implements AuthenticationService{


    private final ProfilRepository profilRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public Boolean profilClientNotExist(RegisterRequest request){
          Optional<Profil> p =profilRepository.findByEmail(request.getEmail());
        return p.isEmpty() || !p.get().getRoles().contains(Role.CLIENT);

    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws ProfilExistException {

        if (!profilClientNotExist(request))throw new ProfilExistException("Email is already registered for Client");
        
        Profil profil = profilRepository.findById(request.getEmail()).orElseGet(() -> {
            Client client = new Client();
            client.setEmail(request.getEmail());
            client.setPassword(passwordEncoder.encode(request.getPassword()));
            client.setRoles(Set.of(Role.CLIENT));
            return clientRepository.save(client);
        });

        profil.getRoles().add(Role.CLIENT);
        profilRepository.save(profil);
        var jwtToken = jwtService.generateToken(profil);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        clientRepository.updateClientTokenByEmail(request.getEmail(), request.getTokenApp());




        var profil = profilRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(profil);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
