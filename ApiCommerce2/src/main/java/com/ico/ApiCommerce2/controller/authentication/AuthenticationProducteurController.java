package com.ico.ApiCommerce2.controller.authentication;

import com.ico.ApiCommerce2.exception.ProfilExistException;
import com.ico.ApiCommerce2.request.AuthenticationRequest;
import com.ico.ApiCommerce2.request.RegisterRequest;
import com.ico.ApiCommerce2.service.authentication.AuthenticationProducteurService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/producteur")
@RequiredArgsConstructor
public class AuthenticationProducteurController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationProducteurController.class);

    private final AuthenticationProducteurService service;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest request) throws ProfilExistException {
            logger.info("/api/auth/producteur/register : {}",request.getEmail());
            return new ResponseEntity<>(service.register(request), HttpStatus.OK);

    }


    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@Valid @RequestBody AuthenticationRequest request)
    {
        logger.info("/api/auth/producteur/register : {}",request.getEmail());
        return new ResponseEntity<>(service.authenticate(request),HttpStatus.OK);
    }

}
