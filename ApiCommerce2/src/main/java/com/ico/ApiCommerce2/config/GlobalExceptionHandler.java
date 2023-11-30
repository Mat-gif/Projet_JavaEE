package com.ico.ApiCommerce2.config;

import com.ico.ApiCommerce2.exception.CommandeNotFoundException;
import com.ico.ApiCommerce2.exception.ProductNotFoundException;
import com.ico.ApiCommerce2.exception.ProfilExistException;
import com.ico.ApiCommerce2.exception.ProfilNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


@ControllerAdvice
public class GlobalExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Personnalisez la réponse en cas d'accès refusé (AccessDeniedException)
        String errorMessage = "Access refuse : " + accessDeniedException.getMessage();
        logger.error("Access refuse : {}",accessDeniedException.getMessage());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(errorMessage);
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // Personnalisez la réponse en cas d'exception d'authentification (AuthenticationException)
        String errorMessage = "Erreur d'authentification : " + authException.getMessage();
        logger.error("Erreur d'authentification : {}",authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(errorMessage);
    }

    @ExceptionHandler(ProfilExistException.class)
    public ResponseEntity<String> handleProfilExistException(ProfilExistException ex) {
        String errorMessage = "Erreur : " + ex.getMessage();
        logger.error("Erreur: {}",ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ProfilNotFoundException.class)
    public ResponseEntity<String> handleProfilNotFoundException(ProfilNotFoundException ex) {
        String errorMessage = "Erreur : " + ex.getMessage();
        logger.error("Erreur: {}",ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        String errorMessage = "Erreur : " + ex.getMessage();
        logger.error("Erreur: {}",ex.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
  
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = "Erreur : " + ex.getMessage();
        logger.error("Erreur: {}",ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CommandeNotFoundException.class)
    public ResponseEntity<String> handleCommandeNotFoundException(CommandeNotFoundException ex) {
        String errorMessage = "Erreur : " + ex.getMessage();
        logger.error("Erreur: {}",ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);


    }


}
