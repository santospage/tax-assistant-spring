package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.SalesMovementsService;
import br.com.santospage.taxassistant.domain.exceptions.SalesMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.SalesMovement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalesMovementController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SalesMovementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesMovementsService service;

    @Test
    void shouldGetByIdFound() throws Exception {
        SalesMovement sm = new SalesMovement();
        sm.setCompanyCode("01");
        sm.setIdTax("TEST001");

        when(service.findById("TEST001")).thenReturn(sm);

        mockMvc.perform(get("/api/sales-movements/{id}", "TEST001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyCode").value("01"))
                .andExpect(jsonPath("$.idTax").value("TEST001"));
    }

    @Test
    void shouldGetByCustomerFound() throws Exception {
        SalesMovement sm = new SalesMovement();
        sm.setCompanyCode("01");
        sm.setCustomerCode("TEST001");

        List<SalesMovement> movements = List.of(sm);

        when(service.findByCustomer("TEST001")).thenReturn(movements);

        mockMvc.perform(get("/api/sales-movements/customer/{customer}", "TEST001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyCode").value("01"))
                .andExpect(jsonPath("$[0].customerCode").value("TEST001"));
    }

    @Test
    void shouldGetByCustomerNotFound() throws Exception {
        when(service.findByCustomer("TEST99"))
                .thenThrow(new SalesMovementNotFoundException("Customer not found"));

        mockMvc.perform(get("/api/fiscal-movements/customer/{customer}", "TEST001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetByProductFound() throws Exception {
        SalesMovement sm = new SalesMovement();
        sm.setCompanyCode("01");
        sm.setProductCode("TEST001");

        List<SalesMovement> movements = List.of(sm);

        when(service.findByProduct("TEST001")).thenReturn(movements);

        mockMvc.perform(get("/api/sales-movements/product/{product}", "TEST001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyCode").value("01"))
                .andExpect(jsonPath("$[0].productCode").value("TEST001"));
    }

    @Test
    void shouldGetByProductNotFound() throws Exception {
        when(service.findByProduct("TEST99"))
                .thenThrow(new SalesMovementNotFoundException("Product not found"));

        mockMvc.perform(get("/api/fiscal-movements/product/{product}", "TEST001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllWithResults() throws Exception {
        SalesMovement sm1 = new SalesMovement();
        sm1.setCompanyCode("01");
        sm1.setIdTax("TEST001");

        SalesMovement sm2 = new SalesMovement();
        sm2.setCompanyCode("01");
        sm2.setIdTax("TEST002");

        List<SalesMovement> movements = List.of(sm1, sm2);

        when(service.findAll()).thenReturn(movements);

        mockMvc.perform(get("/api/sales-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyCode").value("01"))
                .andExpect(jsonPath("$[0].idTax").value("TEST001"))
                .andExpect(jsonPath("$[1].companyCode").value("01"))
                .andExpect(jsonPath("$[1].idTax").value("TEST002"));
    }

    @Test
    void shouldGetAllNoContent() throws Exception {
        when(service.findAll()).thenReturn(List.of()); // lista vazia

        mockMvc.perform(get("/api/sales-movements")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
