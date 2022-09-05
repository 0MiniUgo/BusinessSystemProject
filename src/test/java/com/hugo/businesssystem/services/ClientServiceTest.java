package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.Client;
import com.hugo.businesssystem.entities.dto.ClientDTO;
import com.hugo.businesssystem.repositories.ClientRepository;
import com.hugo.businesssystem.services.exceptions.ResourceNotFoundException;
import com.hugo.businesssystem.util.client.ClientCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientRepository clientRepositoryMock;

    @BeforeEach
    void setUp(){
        List<Client> clients = List.of(ClientCreator.createValidClient());
        BDDMockito.when(clientRepositoryMock.findAll())
                .thenReturn(clients);

        BDDMockito.when(clientRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ClientCreator.createValidClient()));

        BDDMockito.when(clientRepositoryMock.save(ArgumentMatchers.any(Client.class)))
                .thenReturn(ClientCreator.createValidClient());

    }

    @Test
    void findAll_ReturnsListOfClients_WhenSuccessful() {
        String expectedName = ClientCreator.createValidClient().getName();
        List<Client> clients = clientService.findAll();

        Assertions.assertThat(clients)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(clients.get(0).getName()).isEqualTo(expectedName);
    }
    @Test
    void findById_ReturnsClient_WhenSuccessful(){
        Long expectedId = ClientCreator.createValidClient().getId();
        Client client = clientService.findById(1L);

        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getId()).isNotNull().isEqualTo(expectedId);
    }
    @Test
    void findById_ThrowsResourceNotFoundException_WhenClientNotFound(){

        BDDMockito.when(clientRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> clientService.findById(1L));
    }
    @Test
    void insert_ReturnsClient_WhenSuccessful(){
        Client client = clientService.insert(ClientCreator.createValidClientDTO());

        Assertions.assertThat(client)
                .isNotNull()
                .isEqualTo(ClientCreator.createValidClient());
    }
    @Test
    void update_UpdatesClient_WhenSuccessful(){
        Long clientId = ClientCreator.createValidClient().getId();
        ClientDTO clientDTO = ClientCreator.createValidClientDTO();

        Assertions.assertThat(clientService.update(clientId,clientDTO))
                .isNotNull()
                .isEqualTo(ClientCreator.createValidClient());
    }
    @Test
    void delete_RemovesClient_WhenSuccessful(){

        final Long id = 1L;
        clientService.delete(id);
        BDDMockito.verify(clientRepositoryMock).deleteById(id);
    }
}