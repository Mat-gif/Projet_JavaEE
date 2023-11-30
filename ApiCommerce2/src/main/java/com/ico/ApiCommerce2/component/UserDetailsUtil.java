package com.ico.ApiCommerce2.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsUtil {

    public UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (UserDetails) principal;
            }
        }
        // Gérer le cas où l'authentification n'est pas disponible ou que l'utilisateur n'est pas UserDetails
        return null;
    }


    public String getEmail()
    {
        return getUserDetails().getUsername();
    }
}