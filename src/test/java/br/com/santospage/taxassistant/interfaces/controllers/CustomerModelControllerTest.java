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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
        when(customer.getCompanyCode()).thenReturn("01");
        when(customer.getCustomerId()).thenReturn("000001");
        when(customer.getNameCustomer()).thenReturn("CUSTOMER 001");
        when(customer.getTypeCustomer()).thenReturn("S");

        when(service.findByCompanyAndId("01", "000001")).thenReturn(customer);

        mockMvc.perform(get("/api/customers")
                                .param("company", "01")
                                .param("id", "000001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("000001"))
                .andExpect(jsonPath("$.nameCustomer").value("CUSTOMER 001"));
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
        when(customer1.getCustomerId()).thenReturn("000001");
        when(customer1.getNameCustomer()).thenReturn("CUSTOMER 001");

        CustomerModel customer2 = mock(CustomerModel.class);
        when(customer2.getCustomerId()).thenReturn("000002");
        when(customer2.getNameCustomer()).thenReturn("CUSTOMER 002");

        List<CustomerModel> list = List.of(customer1, customer2);
        Page<CustomerModel> page = new PageImpl<>(list, PageRequest.of(0, 10), list.size());

        // Mock
        when(service.findAll(any(), any())).thenReturn(page);

        mockMvc.perform(get("/api/customers")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId").value("000001"))
                .andExpect(jsonPath("$[0].nameCustomer").value("CUSTOMER 001"))
                .andExpect(jsonPath("$[1].customerId").value("000002"))
                .andExpect(jsonPath("$[1].nameCustomer").value("CUSTOMER 002"));
    }

    @Test
    void shouldGetAllNoContent() throws Exception {
        PageImpl<CustomerModel> emptyPage = new PageImpl<>(
                Collections.emptyList(),
                PageRequest.of(0, 10),
                0
        );

        when(service.findAll(any(), any())).thenReturn(emptyPage);

        mockMvc.perform(get("/api/customers")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}