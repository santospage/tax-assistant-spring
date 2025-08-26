package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementsService;
import br.com.santospage.taxassistant.domain.exceptions.FiscalMovementNotFoundException;
import br.com.santospage.taxassistant.domain.exceptions.GlobalExceptionHandler;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
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
    private FiscalMovementDTO fiscalDTO;

    @BeforeEach
    void setup() {
        service = mock(FiscalMovementsService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FiscalMovementController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        fiscalDTO = new FiscalMovementDTO();
        fiscalDTO.idMovement = "001";
        fiscalDTO.tableMovement = "TAB01";
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.findById("001")).thenReturn(fiscalDTO);

        mockMvc.perform(get("/api/fiscal-movements/001")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMovement").value("001"))
                .andExpect(jsonPath("$.tableMovement").value("TAB01"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.findById("NOTFOUND"))
                .thenThrow(new FiscalMovementNotFoundException("FiscalMovement not found with id: NOTFOUND"));

        mockMvc.perform(get("/api/fiscal-movements/NOTFOUND")
                                .accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("FiscalMovement not found with id: NOTFOUND"));
    }

    @Test
    void testGetByTableCodeFound() throws Exception {
        when(service.findByTable("TAB01")).thenReturn(List.of(fiscalDTO));

        mockMvc.perform(get("/api/fiscal-movements/table/TAB01")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idMovement").value("001"));
    }

    @Test
    void testGetByTableCodeNotFound() throws Exception {
        when(service.findByTable("INVALID"))
                .thenThrow(new FiscalMovementNotFoundException("No FiscalMovements found for table: INVALID"));

        mockMvc.perform(get("/api/fiscal-movements/table/INVALID")
                                .accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No FiscalMovements found for table: INVALID"));
    }

    @Test
    void testGetAllFound() throws Exception {
        when(service.findAll()).thenReturn(List.of(fiscalDTO));

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idMovement").value("001"));
    }

    @Test
    void testGetAllNoContent() throws Exception {
        when(service.findAll()).thenThrow(new FiscalMovementNotFoundException("No FiscalMovements found"));

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No FiscalMovements found"));
    }
}
