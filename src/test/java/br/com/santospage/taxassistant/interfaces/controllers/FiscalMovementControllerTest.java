package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementsService;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FiscalMovementControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FiscalMovementsService service;

    @InjectMocks
    private FiscalMovementController controller;

    private FiscalMovementDTO dto;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        dto = new FiscalMovementDTO();
        dto.idMovement = "F2D123456";
        dto.taxCode = "ICMS";
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.findById("F2D123456")).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/api/fiscal-movements/F2D123456")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMovement").value("F2D123456"))
                .andExpect(jsonPath("$.taxCode").value("ICMS"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.findById("NOTFOUND")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/fiscal-movements/NOTFOUND")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetByTableCodeFound() throws Exception {
        when(service.findByTable(eq("SD2"))).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/fiscal-movements")
                                .param("table", "SD2")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idMovement").value("F2D123456"))
                .andExpect(jsonPath("$[0].taxCode").value("ICMS"));
    }

    @Test
    void testGetByTableCodeNotFound() throws Exception {
        when(service.findByTable(eq("EMPTY"))).thenReturn(List.of());

        mockMvc.perform(get("/api/fiscal-movements")
                                .param("table", "EMPTY")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllWithResults() throws Exception {
        when(service.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idMovement").value("F2D123456"))
                .andExpect(jsonPath("$[0].taxCode").value("ICMS"));
    }

    @Test
    void testGetAllNoContent() throws Exception {
        when(service.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
