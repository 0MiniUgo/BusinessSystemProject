package com.hugo.businesssystem.resources;

import com.hugo.businesssystem.entities.Client;
import com.hugo.businesssystem.entities.dto.ClientDTO;
import com.hugo.businesssystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> findAll(){
        List<Client> clients = service.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        Client client = service.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody ClientDTO clientDTO){

        Client client = service.insert(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(client);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
