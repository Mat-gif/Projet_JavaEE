package com.ico.ApiCommerce2.controller.authentication;


import com.ico.ApiCommerce2.exception.ProfilExistException;
import com.ico.ApiCommerce2.request.AuthenticationRequest;
import com.ico.ApiCommerce2.service.authentication.AuthenticationClientService;
import com.ico.ApiCommerce2.request.RegisterRequest;
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
@RequestMapping("/api/auth/client")
@RequiredArgsConstructor
public class AuthenticationClientController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationClientController.class);

    private final AuthenticationClientService service;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest request) throws ProfilExistException {
        logger.info("/api/auth/client/register : {}",request.getEmail());
        return new ResponseEntity<>(service.register(request), HttpStatus.OK);

    }


    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@Valid @RequestBody AuthenticationRequest request)
    {
        logger.info("/api/auth/client/authenticate : {}",request.getEmail());
        return new ResponseEntity<>(service.authenticate(request),HttpStatus.OK);
    }

}
