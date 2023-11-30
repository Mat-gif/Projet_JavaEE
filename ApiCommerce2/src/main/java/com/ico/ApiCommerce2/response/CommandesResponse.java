package com.ico.ApiCommerce2.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
public class CommandesResponse {
    private ArrayList<CommandeResponse> commandes;

    public CommandesResponse() {
        this.commandes =  new ArrayList<>();
    }
}
