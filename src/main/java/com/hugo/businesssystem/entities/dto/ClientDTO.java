package com.hugo.businesssystem.entities.dto;

import com.hugo.businesssystem.entities.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private String name;

    public ClientDTO(Client client){
        this.name = client.getName();
    }
}
