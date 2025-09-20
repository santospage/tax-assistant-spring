package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.domain.exceptions.CustomerNotFoundException;
import br.com.santospage.taxassistant.domain.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @Test
    void shouldGetByIdFound() throws Exception {
        Customer customer = new Customer();
        customer.setCompany("01");
        customer.setId("TEST001");
        customer.setName("CUSTOMER TEST001");
        customer.setTypeCustomer("F");

        when(service.findByFilialAndId("01", "TEST001")).thenReturn(customer);

        mockMvc.perform(get("/api/customers")
                                .param("company", "01")
                                .param("id", "TEST001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("TEST001"))
                .andExpect(jsonPath("$.name").value("CUSTOMER TEST001"));
    }

    @Test
    void shouldGetByIdNotFound() throws Exception {
        when(service.findByFilialAndId("01", "TEST999"))
                .thenThrow(new CustomerNotFoundException(""));

        mockMvc.perform(get("/api/customers")
                                .param("company", "01")
                                .param("id", "TEST999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllSuccess() throws Exception {
        Customer customer1 = new Customer();
        customer1.setCompany("01");
        customer1.setId("TEST001");
        customer1.setName("CUSTOMER TEST001");
        customer1.setTypeCustomer("F");

        Customer customer2 = new Customer();
        customer2.setCompany("01");
        customer2.setId("TEST002");
        customer2.setName("CUSTOMER TEST002");
        customer2.setTypeCustomer("F");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        // Mock
        when(service.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/customers")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("TEST001"))
                .andExpect(jsonPath("$[0].name").value("CUSTOMER TEST001"))
                .andExpect(jsonPath("$[1].id").value("TEST002"))
                .andExpect(jsonPath("$[1].name").value("CUSTOMER TEST002"));
    }

    @Test
    void shouldGetAllNoContent() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/customers")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}