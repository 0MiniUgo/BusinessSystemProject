package com.hugo.businesssystem.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo.businesssystem.entities.Client;
import com.hugo.businesssystem.entities.dto.ClientDTO;
import com.hugo.businesssystem.repositories.ClientRepository;
import com.hugo.businesssystem.services.ClientService;
import com.hugo.businesssystem.util.client.ClientCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientResource.class)
class ClientResourceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    ClientService clientService;
    @MockBean
    ClientRepository clientRepository;

    @Test
    void findAll_ReturnsResponseEntityOfListOfClients_WhenSuccessful() throws Exception {

        BDDMockito.when(clientService.findAll())
                .thenReturn(List.of(ClientCreator.createValidClient()));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Rick")));

    }
    @Test
    void findById_ReturnsResponseEntityOfClient_WhenSuccessful() throws Exception {

        BDDMockito.when(clientService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClientCreator.createValidClient());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/clients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Rick")));

    }
    @Test
    void insert_ReturnsResponseEntityOfClient_WhenSuccessful() throws Exception {

        BDDMockito.when(clientService.insert(ArgumentMatchers.any(ClientDTO.class)))
                .thenReturn(ClientCreator.createValidClient());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ClientCreator.createValidClientDTO()));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is(("Rick"))));
    }
    @Test
    void update_UpdatesClient_WhenSuccessful() throws Exception{

        BDDMockito.when(clientService.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(ClientDTO.class)))
                .thenReturn(ClientCreator.createValidClient());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ClientCreator.createValidClientDTO()));


        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is(("Rick"))));
    }
    @Test
    void delete_DeletesClient_WhenSuccessful() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/clients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}