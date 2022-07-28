package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.Client;
import com.hugo.businesssystem.entities.dto.ClientDTO;
import com.hugo.businesssystem.repositories.ClientRepository;
import com.hugo.businesssystem.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll(){
        return repository.findAll();
    }

    public Client findById(Long id){
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Client insert(ClientDTO clientDTO){
        Client client = toEntity(clientDTO);

        return repository.save(client);
    }

    private Client toEntity(ClientDTO clientDTO){

        return new Client(null, clientDTO.getName());
    }
}
