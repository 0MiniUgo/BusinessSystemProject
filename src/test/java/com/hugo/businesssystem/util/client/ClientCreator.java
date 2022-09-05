package com.hugo.businesssystem.util.client;

import com.hugo.businesssystem.entities.Client;
import com.hugo.businesssystem.entities.dto.ClientDTO;

public class ClientCreator {

    public static Client createClientToBeSaved(){
        return Client.builder()
                .name("Rick")
                .build();
    }
    public static Client createValidClient(){
        return Client.builder()
                .id(1L)
                .name("Rick")
                .build();
    }
    public static ClientDTO createValidClientDTO(){
        return ClientDTO.builder()
                .name("Rick")
                .build();
    }
}
