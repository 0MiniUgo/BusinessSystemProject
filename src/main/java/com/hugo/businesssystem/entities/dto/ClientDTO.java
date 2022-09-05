package com.hugo.businesssystem.entities.dto;

import com.hugo.businesssystem.entities.Client;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private String name;

    public ClientDTO(Client client){
        this.name = client.getName();
    }
}
