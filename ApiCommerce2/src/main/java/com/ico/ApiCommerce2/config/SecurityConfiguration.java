package com.ico.ApiCommerce2.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class  SecurityConfiguration {
    private final JwtAuthenficationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("globalExceptionHandler")
    GlobalExceptionHandler globalExceptionHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                    .requestMatchers("/api/producteur/**").hasRole("PRODUCTEUR")
                    .requestMatchers("/api/client/**").hasRole("CLIENT")
                    .requestMatchers("/api/auth/**","/api/produit")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(globalExceptionHandler) // Gère les exceptions d'accès refusé
                    .authenticationEntryPoint(globalExceptionHandler) // Gère les exceptions d'authentification
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
