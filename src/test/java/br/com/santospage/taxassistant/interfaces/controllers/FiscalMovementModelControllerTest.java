package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementService;
import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.FiscalMovementModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FiscalMovementController.class)
@AutoConfigureMockMvc(addFilters = false)
public class FiscalMovementModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FiscalMovementService service;

    @Test
    void shouldGetByIdFound() throws Exception {
        // Given
        FiscalMovementModel fiscalMovement = mock(FiscalMovementModel.class);
        when(fiscalMovement.getMovementId()).thenReturn("000001");
        when(fiscalMovement.getCompanyCode()).thenReturn("01");
        when(fiscalMovement.getMovementTable()).thenReturn("TAB01");
        when(fiscalMovement.isActive()).thenReturn(true);

        when(service.findById("000001")).thenReturn(fiscalMovement);

        // When / Then
        mockMvc.perform(get("/api/fiscal-movements/{id}", "000001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movementId").value("000001"))
                .andExpect(jsonPath("$.companyCode").value("01"))
                .andExpect(jsonPath("$.movementTable").value("TAB01"));

        verify(service).findById("000001");
    }

    @Test
    void shouldGetByIdNotFound() throws Exception {
        when(service.findById("TEST999"))
                .thenThrow(new ResourceNotFoundException("Movement not found: TEST999"));

        mockMvc.perform(get("/api/fiscal-movements/{id}", "TEST999")
                                .param("id", "TEST999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetByTableFound() throws Exception {
        // Given
        FiscalMovementModel result = mock(FiscalMovementModel.class);
        when(result.getMovementTable()).thenReturn("TAB01");
        when(result.isActive()).thenReturn(true);

        when(service.findByTableMovement("TAB01")).thenReturn(List.of(result));

        // When / Then
        mockMvc.perform(get("/api/fiscal-movements/table/{table}", "TAB01")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movementTable").value("TAB01"));

        verify(service).findByTableMovement("TAB01");
    }

    @Test
    void shouldGetByTableNotFound() throws Exception {
        when(service.findByTableMovement("TAB999"))
                .thenThrow(new ResourceNotFoundException("Movement table not found: TAB999"));

        mockMvc.perform(get("/api/fiscal-movements/table/{table}", "TAB999")
                                .param("table", "TAB999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllWithResults() throws Exception {
        FiscalMovementModel fm1 = mock(FiscalMovementModel.class);
        when(fm1.getMovementId()).thenReturn("000001");
        when(fm1.isActive()).thenReturn(true);

        FiscalMovementModel fm2 = mock(FiscalMovementModel.class);
        when(fm2.getMovementId()).thenReturn("000002");
        when(fm2.isActive()).thenReturn(true);

        List<FiscalMovementModel> list = List.of(fm1, fm2);
        Page<FiscalMovementModel> page = new PageImpl<>(
                list, PageRequest.of(0, 10)
                , list.size()
        );

        // Mock
        when(service.findAll(any(), any())).thenReturn(page);

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movementId").value("000001"))
                .andExpect(jsonPath("$[1].movementId").value("000002"));
    }

    @Test
    void shouldGetAllNotContent() throws Exception {
        PageImpl<FiscalMovementModel> emptyPage = new PageImpl<>(
                Collections.emptyList(),
                PageRequest.of(0, 10),
                0
        );

        when(service.findAll(any(), any())).thenReturn(emptyPage);

        mockMvc.perform(get("/api/fiscal-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}