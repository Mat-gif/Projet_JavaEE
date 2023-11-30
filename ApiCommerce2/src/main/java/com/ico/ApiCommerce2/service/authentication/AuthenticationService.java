package com.ico.ApiCommerce2.service.authentication;

import com.ico.ApiCommerce2.exception.ProfilExistException;
import com.ico.ApiCommerce2.request.AuthenticationRequest;
import com.ico.ApiCommerce2.response.AuthenticationResponse;
import com.ico.ApiCommerce2.request.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request) throws ProfilExistException;
//    <T extends AuthenticationResponse> authenticate(AuthenticationRequest request) ;
}
