package br.com.gpiagentini.api.adapter.controllers;

import br.com.gpiagentini.api.adapters.controllers.ContactController;
import br.com.gpiagentini.api.adapters.controllers.advisors.GlobalExceptionHandler;
import br.com.gpiagentini.api.application.dto.NewContactData;
import br.com.gpiagentini.api.application.dto.RetrieveContactData;
import br.com.gpiagentini.api.application.dto.RetrieveProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateContactData;
import br.com.gpiagentini.api.application.port.in.IContactApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IContactApplicationService contactApplicationService;

    @InjectMocks
    private ContactController contactController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void getAllContacts_shouldReturnContacts() throws Exception {
        // Mocking
        when(contactApplicationService.getAllContacts(Mockito.anyString())).thenReturn(List.of());

        // Performing the request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contatos"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllContacts_ShouldReturnSpecificFields() throws Exception {
        var mockContact1 = new RetrieveContactData(1L, "Teste 1", "Teste 1", LocalDateTime.now(), null);
        var mockContact2 = new RetrieveContactData(2L, "Teste 2", "Teste 2", LocalDateTime.now(), null);
        var mockList = List.of(mockContact1, mockContact2);
        var fields = "id,nome";

        when(contactApplicationService.getAllContacts("")).thenReturn(mockList);

        objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter("DynamicFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(","))));
        mockMvc.perform(get("/api/v1/contatos")
                        .param("fields", fields))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockList)));
    }

    @Test
    void getContactById_shouldReturnContact() throws Exception {
        var contact = new RetrieveContactData(1L, "Teste", "Teste", LocalDateTime.now(), null);

        // Mocking
        when(contactApplicationService.getContactById(Mockito.anyLong())).thenReturn(contact);

        // Performing the request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contatos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getContractById_ShouldReturnNotFound() throws Exception {
        when(contactController.getContactById(1L)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(get("/api/v1/profissionais/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createNewContact_shouldCreateContact() throws Exception {
        NewContactData newContactData = new NewContactData("Teste", "Teste", 1L);
        String json = objectMapper.writeValueAsString(newContactData);

        Mockito.when(contactApplicationService.saveNewContact(Mockito.any(NewContactData.class))).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/contatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createNewContact_shouldReturnBadRequest_whenInvalidProfessional() throws Exception {
        NewContactData newContactData = new NewContactData("Teste", "Teste", 1L);
        String json = objectMapper.writeValueAsString(newContactData);

        Mockito.when(contactApplicationService.saveNewContact(Mockito.any(NewContactData.class))).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/contatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void createNewContact_shouldReturnBadRequest_whenInvalidData() throws Exception {
        when(contactApplicationService.saveNewContact(Mockito.any(NewContactData.class))).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/contatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Teste\" }"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void deleteContactById_shouldDeleteContact() throws Exception {
        doNothing().when(contactApplicationService).deleteContactById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/contatos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Contato excluído."));
    }

    @Test
    void updateContactById_shouldUpdateContact() throws Exception {
        UpdateContactData updateContactData = new UpdateContactData("Teste", "Teste", 1L);
        String json = objectMapper.writeValueAsString(updateContactData);

        doNothing().when(contactApplicationService).updateContactInformation(1L, updateContactData);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/contatos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Contato alterado."));
    }

    @Test
    void updateContactById_shouldBadRequest_whenProfessionalIsInvalid() throws Exception {
        UpdateContactData updateContactData = new UpdateContactData("Teste", "Teste", 1L);
        String json = objectMapper.writeValueAsString(updateContactData);

        doThrow(IllegalArgumentException.class).when(contactApplicationService).updateContactInformation(1L, updateContactData);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/contatos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

}
