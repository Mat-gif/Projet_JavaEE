package com.ico.ApiCommerce2.request;

import com.ico.ApiCommerce2.response.CommandeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
public class CommandesRequest {
    @NotBlank
    private ArrayList<CommandeResponse> commandes;

    public CommandesRequest() {
        this.commandes =  new ArrayList<>();
    }
}
