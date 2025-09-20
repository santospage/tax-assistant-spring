package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementsService;
import br.com.santospage.taxassistant.domain.exceptions.FiscalMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.FiscalMovement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FiscalMovementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FiscalMovementsService service;

    @Test
    void shouldGetByIdFound() throws Exception {
        FiscalMovement fm = new FiscalMovement();
        fm.setCompanyCode("01");
        fm.setMovementId("TEST001");
        fm.setIdBase("BASE001");
        fm.setIdRelationship("REL001");

        when(service.findById("TEST001")).thenReturn(fm);

        mockMvc.perform(get("/api/fiscal-movements/{id}", "TEST001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movementId").value("TEST001"))
                .andExpect(jsonPath("$.idBase").value("BASE001"))
                .andExpect(jsonPath("$.idRelationship").value("REL001"));
    }

    @Test
    void shouldGetByIdNotFound() throws Exception {
        when(service.findById("TEST999"))
                .thenThrow(new FiscalMovementNotFoundException(""));

        mockMvc.perform(get("/api/fiscal-movements/{id}", "TEST999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetByTableFound() throws Exception {
        FiscalMovement fm = new FiscalMovement();
        fm.setCompanyCode("01");
        fm.setMovementId("TEST001");
        fm.setIdBase("BASE001");
        fm.setIdRelationship("REL001");

        List<FiscalMovement> movements = List.of(fm);

        when(service.findByTableMovement("TABLE01")).thenReturn(movements);

        mockMvc.perform(get("/api/fiscal-movements/table/{table}", "TABLE01")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movementId").value("TEST001"))
                .andExpect(jsonPath("$[0].idBase").value("BASE001"))
                .andExpect(jsonPath("$[0].idRelationship").value("REL001"));
    }

    @Test
    void shouldGetByTableNotFound() throws Exception {
        when(service.findByTableMovement("TABLE99"))
                .thenThrow(new FiscalMovementNotFoundException("Table not found"));

        mockMvc.perform(get("/api/fiscal-movements/table/{table}", "TABLE99")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllWithResults() throws Exception {
        FiscalMovement fm = new FiscalMovement();
        fm.setMovementId("TEST001");
        fm.setIdBase("BASE001");
        fm.setIdRelationship("REL001");

        List<FiscalMovement> movements = List.of(fm);

        when(service.findAll()).thenReturn(movements);

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movementId").value("TEST001"))
                .andExpect(jsonPath("$[0].idBase").value("BASE001"))
                .andExpect(jsonPath("$[0].idRelationship").value("REL001"));
    }

    @Test
    void shouldGetAllNoContent() throws Exception {
        when(service.findAll()).thenReturn(List.of()); // lista vazia

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}