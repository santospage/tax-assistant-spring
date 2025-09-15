/*
package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementsService;
import br.com.santospage.taxassistant.domain.models.FiscalMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FiscalMovementController.class)
@AutoConfigureMockMvc(addFilters = false)
class FiscalMovementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FiscalMovementsService service;

    private FiscalMovement fiscal1;
    private FiscalMovement fiscal2;

    @BeforeEach
    void setup() {
        fiscal1 = new FiscalMovement();
        fiscal1.setCompanyCode("D RJ 01");
        fiscal1.setTable("TAB01");
        fiscal1.setMovementId("001");

        fiscal2 = new FiscalMovement();
        fiscal2.setCompanyCode("D RJ 02");
        fiscal2.setTable("TAB02");
        fiscal2.setMovementId("002");
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(service.findById("001")).thenReturn(fiscal1);

        mockMvc.perform(get("/api/fiscal-movements/001")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("001"))
                .andExpect(jsonPath("$.tableMovement").value("TAB01"))
                .andExpect(jsonPath("$.companyCode").value("D RJ 01"));

        verify(service).findById("001");
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(service.findById("999")).thenThrow(new RuntimeException("Fiscal movement not found"));

        mockMvc.perform(get("/api/fiscal-movements/999")
                                .accept("application/json"))
                .andExpect(status().isInternalServerError());

        verify(service).findById("999");
    }

    @Test
    void testGetByTableFound() throws Exception {
        when(service.findByTableMovement("TAB01")).thenReturn(List.of(fiscal1));

        mockMvc.perform(get("/api/fiscal-movements/table/TAB01")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("001"))
                .andExpect(jsonPath("$[0].tableMovement").value("TAB01"))
                .andExpect(jsonPath("$[0].companyCode").value("D RJ 01"));

        verify(service).findByTableMovement("TAB01");
    }

    @Test
    void testGetByTableNotFound() throws Exception {
        when(service.findByTableMovement("TAB99")).thenReturn(List.of());

        mockMvc.perform(get("/api/fiscal-movements/table/TAB99")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        verify(service).findByTableMovement("TAB99");
    }

    @Test
    void testGetAll() throws Exception {
        when(service.findAll()).thenReturn(List.of(fiscal1, fiscal2));

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("001"))
                .andExpect(jsonPath("$[0].tableMovement").value("TAB01"))
                .andExpect(jsonPath("$[1].id").value("002"))
                .andExpect(jsonPath("$[1].tableMovement").value("TAB02"));

        verify(service).findAll();
    }

    @Test
    void testGetAllEmpty() throws Exception {
        when(service.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept("application/json"))
                .andExpect(status().isNoContent());

        verify(service).findAll();
    }
}
*/