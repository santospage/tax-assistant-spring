package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.CustomerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
class CustomerModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetByIdFound() throws Exception {
        CustomerModel customer = mock(CustomerModel.class);
        when(customer.getCompany()).thenReturn("01");
        when(customer.getId()).thenReturn("000001");
        when(customer.getName()).thenReturn("CUSTOMER 001");
        when(customer.getTypeCustomer()).thenReturn("S");

        when(service.findByCompanyAndId("01", "000001")).thenReturn(customer);

        mockMvc.perform(get("/api/customers")
                                .param("company", "01")
                                .param("id", "000001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("000001"))
                .andExpect(jsonPath("$.name").value("CUSTOMER 001"));
    }

    @Test
    void shouldGetByIdNotFound() throws Exception {
        when(service.findByCompanyAndId("01", "TEST999"))
                .thenThrow(new ResourceNotFoundException("Customer not found: TEST999"));

        mockMvc.perform(get("/api/customers")
                                .param("company", "01")
                                .param("id", "TEST999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllSuccess() throws Exception {
        CustomerModel customer1 = mock(CustomerModel.class);
        when(customer1.getCompany()).thenReturn("01");
        when(customer1.getId()).thenReturn("000001");
        when(customer1.getName()).thenReturn("CUSTOMER 001");
        when(customer1.getTypeCustomer()).thenReturn("F");

        CustomerModel customer2 = mock(CustomerModel.class);
        when(customer2.getCompany()).thenReturn("01");
        when(customer2.getId()).thenReturn("000002");
        when(customer2.getName()).thenReturn("CUSTOMER 002");
        when(customer2.getTypeCustomer()).thenReturn("L");

        List<CustomerModel> customers = Arrays.asList(customer1, customer2);

        // Mock
        when(service.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/customers")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("000001"))
                .andExpect(jsonPath("$[0].name").value("CUSTOMER 001"))
                .andExpect(jsonPath("$[1].id").value("000002"))
                .andExpect(jsonPath("$[1].name").value("CUSTOMER 002"));
    }

    @Test
    void shouldGetAllNoContent() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/customers")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}