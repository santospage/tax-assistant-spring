/*
package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.domain.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void shouldReturnAllCustomers() throws Exception {
        // Mockando os clientes corretamente
        List<Customer> customers = List.of(
                new Customer("000001", "CUSTOMER 001", "D RJ"),
                new Customer("000002", "CUSTOMER 002", "D RJ")
        );
        when(customerService.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/customers")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("000001"))
                .andExpect(jsonPath("$[0].name").value("CUSTOMER 001"))
                .andExpect(jsonPath("$[0].company").value("D RJ"))
                .andExpect(jsonPath("$[1].id").value("000002"))
                .andExpect(jsonPath("$[1].name").value("CUSTOMER 002"))
                .andExpect(jsonPath("$[1].company").value("D RJ"));

        verify(customerService).findAll();
    }

    @Test
    void shouldReturnCustomerById() throws Exception {
        Customer customer = new Customer();
        when(customerService.findByFilialAndId("D RJ", "000001"))
                .thenReturn(customer);


        mockMvc.perform(get("/api/customers/000001")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("000001"))
                .andExpect(jsonPath("$.name").value("CUSTOMER 001"))
                .andExpect(jsonPath("$.company").value("D RJ"));

        verify(customerService).findByFilialAndId("D RJ", "000001");
    }

    @Test
    void shouldReturnNotFoundForInvalidCustomerId() throws Exception {
        when(customerService.findByFilialAndId("D RJ", "999999"))
                .thenReturn(null);

        mockMvc.perform(get("/api/customers/999999")
                                .accept("application/json"))
                .andExpect(status().isNotFound());

        verify(customerService).findByFilialAndId("D RJ", "999999");
    }
}
*/
