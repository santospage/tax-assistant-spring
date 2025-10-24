package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.SalesMovementService;
import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.SalesMovementModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalesMovementController.class)
@AutoConfigureMockMvc(addFilters = false)
class SalesMovementModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesMovementService service;

    @Test
    void shouldGetByIdFound() throws Exception {
        // Given
        SalesMovementModel salesMovement = mock(SalesMovementModel.class);
        when(salesMovement.getTaxId()).thenReturn("000001");
        when(salesMovement.getCompanyCode()).thenReturn("01");
        when(salesMovement.isActive()).thenReturn(true);

        when(service.findById("000001")).thenReturn(salesMovement);

        // When / Then
        mockMvc.perform(get("/api/sales-movements/{id}", "000001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taxId").value("000001"))
                .andExpect(jsonPath("$.companyCode").value("01"));

        verify(service).findById("000001");
    }

    @Test
    void shouldGetByIdNotFound() throws Exception {
        when(service.findById("TEST999"))
                .thenThrow(new ResourceNotFoundException("Movement not found: TEST999"));

        mockMvc.perform(get("/api/sales-movements/{id}", "TEST999")
                                .param("id", "TEST999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetByCustomerFound() throws Exception {
        // Given
        SalesMovementModel result = mock(SalesMovementModel.class);
        when(result.getCustomerCode()).thenReturn("CUSTOMER01");
        when(result.isActive()).thenReturn(true);

        when(service.findByCustomer("CUSTOMER01")).thenReturn(List.of(result));

        // When / Then
        mockMvc.perform(get(
                        "/api/sales-movements/customer/{customer}"
                        , "CUSTOMER01"
                )
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerCode")
                                   .value("CUSTOMER01"));

        verify(service).findByCustomer("CUSTOMER01");
    }

    @Test
    void shouldGetByCustomerNotFound() throws Exception {
        when(service.findByCustomer("TEST99"))
                .thenThrow(new ResourceNotFoundException("Customer not found: TEST99"));

        mockMvc.perform(get(
                        "/api/sales-movements/customer/{customer}"
                        , "TEST99"
                )
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetByProductFound() throws Exception {
        // Given
        SalesMovementModel result = mock(SalesMovementModel.class);
        when(result.getProductCode()).thenReturn("PRODUCT01");
        when(result.isActive()).thenReturn(true);

        when(service.findByProduct("PRODUCT01")).thenReturn(List.of(result));

        // When / Then
        mockMvc.perform(get(
                        "/api/sales-movements/product/{product}"
                        , "PRODUCT01"
                )
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productCode")
                                   .value("PRODUCT01"));

        verify(service).findByProduct("PRODUCT01");
    }

    @Test
    void shouldGetByProductNotFound() throws Exception {
        when(service.findByProduct("TEST99"))
                .thenThrow(new ResourceNotFoundException("Product not found: TEST99"));

        mockMvc.perform(get(
                        "/api/sales-movements/product/{product}"
                        , "TEST99"
                )
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllWithResults() throws Exception {
        // Given
        SalesMovementModel sm1 = mock(SalesMovementModel.class);
        when(sm1.getTaxId()).thenReturn("000001");
        when(sm1.isActive()).thenReturn(true);

        SalesMovementModel sm2 = mock(SalesMovementModel.class);
        when(sm2.getTaxId()).thenReturn("000002");
        when(sm2.isActive()).thenReturn(true);

        List<SalesMovementModel> movements = List.of(sm1, sm2);

        when(service.findAll()).thenReturn(movements);

        // When / Then
        mockMvc.perform(get("/api/sales-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taxId").value("000001"))
                .andExpect(jsonPath("$[1].taxId").value("000002"));

        verify(service).findAll();
    }

    @Test
    void shouldGetAllNotContent() throws Exception {
        when(service.findAll()).thenReturn(List.of()); // lista vazia

        mockMvc.perform(get("/api/sales-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
