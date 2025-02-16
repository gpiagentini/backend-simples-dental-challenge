package br.com.gpiagentini.api.adapter.controllers;

import br.com.gpiagentini.api.adapters.controllers.ProfessionalController;
import br.com.gpiagentini.api.adapters.controllers.advisors.GlobalExceptionHandler;
import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.RetrieveProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;
import br.com.gpiagentini.api.application.port.in.IProfessionalApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProfessionalControllerTest {

    private final LocalDate globalBirthDate = LocalDate.of(1999, 2, 2);
    private final LocalDateTime globalCreatedTime = LocalDateTime.of(2025, 2, 15, 0, 0, 0);


    private MockMvc mockMvc;

    @Mock
    private IProfessionalApplicationService professionalApplicationService;

    @InjectMocks
    private ProfessionalController professionalController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(professionalController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void getProfessionalById_ShouldReturnProfessional() throws Exception {
        var mockProfessional = new RetrieveProfessionalData(1L, "Professional Name", "Professional Position", globalBirthDate, globalCreatedTime, true);

        when(professionalApplicationService.getProfessionalById(1L)).thenReturn(mockProfessional);

        objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter("DynamicFilter", SimpleBeanPropertyFilter.serializeAll()));
        mockMvc.perform(get("/api/v1/profissionais/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockProfessional)));
    }

    @Test
    void getProfessionalById_ShouldReturnNotFound() throws Exception {
        when(professionalApplicationService.getProfessionalById(1L)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(get("/api/v1/profissionais/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllProfessionals_ShouldReturnAllFields() throws Exception {
        var mockProfessional1 = new RetrieveProfessionalData(1L, "Teste 1", "Desenvolvedor", globalBirthDate, globalCreatedTime, true);
        var mockProfessional2 = new RetrieveProfessionalData(2L, "Teste 2", "Testes", globalBirthDate, globalCreatedTime, true);
        var mockList = List.of(mockProfessional1, mockProfessional2);

        when(professionalApplicationService.getAllProfessionals(""))
                .thenReturn(mockList);

        objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter("DynamicFilter", SimpleBeanPropertyFilter.serializeAll()));
        mockMvc.perform(get("/api/v1/profissionais"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockList)));
    }

    @Test
    void getAllProfessionals_ShouldReturnSpecificFields() throws Exception {
        var mockProfessional1 = new RetrieveProfessionalData(1L, "Teste 1", "Desenvolvedor", globalBirthDate, globalCreatedTime, true);
        var mockProfessional2 = new RetrieveProfessionalData(2L, "Teste 2", "Testes", globalBirthDate, globalCreatedTime, true);
        var mockList = List.of(mockProfessional1, mockProfessional2);
        var fields = "id,nome";

        when(professionalApplicationService.getAllProfessionals(""))
                .thenReturn(mockList);

        objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter("DynamicFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(","))));
        mockMvc.perform(get("/api/v1/profissionais")
                        .param("fields", fields))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockList)));
    }

    @Test
    void createProfessional_ShouldReturnSuccessMessage() throws Exception {
        var newProfessionalData = new NewProfessionalData("Name", "Developer", LocalDate.now());
        when(professionalApplicationService.createNewProfessional(any(NewProfessionalData.class))).thenReturn("1");

        mockMvc.perform(post("/api/v1/profissionais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProfessionalData)))
                .andExpect(status().isOk())
                .andExpect(content().string("Sucesso, profissional com id 1 cadastrado."));
    }

    @Test
    void createProfessional_ShouldReturnBadRequest() throws Exception {
        var newProfessionalData = new NewProfessionalData("Name", null, null);
        when(professionalApplicationService.createNewProfessional(any(NewProfessionalData.class))).thenReturn("1");

        mockMvc.perform(post("/api/v1/profissionais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProfessionalData)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteProfessional_ShouldReturnSuccessMessage() throws Exception {
        doNothing().when(professionalApplicationService).deleteProfessionalById(1L);

        mockMvc.perform(delete("/api/v1/profissionais/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sucesso, profissional excluído"));
    }

    @Test
    void updateProfessionalInfo_ShouldReturnSuccessMessage() throws Exception {
        UpdateProfessionalData updateProfessional = new UpdateProfessionalData("Name", "Developer", LocalDate.now());
        doNothing().when(professionalApplicationService).updateProfessionalData(eq(1L), any());

        mockMvc.perform(put("/api/v1/profissionais/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateProfessional)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro alterado."));
    }


}