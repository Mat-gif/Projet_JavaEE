package com.ico.ApiCommerce2.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationProducteurResponse  {
    private ProducteurResponse producteur;
    private String token;
}
