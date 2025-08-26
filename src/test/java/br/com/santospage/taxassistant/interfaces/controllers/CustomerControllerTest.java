package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.domain.exceptions.CustomerNotFoundException;
import br.com.santospage.taxassistant.domain.exceptions.GlobalExceptionHandler;
import br.com.santospage.taxassistant.interfaces.dtos.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerDTO customerDTO;

    @BeforeEach
    void setup() {
        customerService = mock(CustomerService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new CustomerController(customerService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        customerDTO = new CustomerDTO();
        customerDTO.id = "000001";
        customerDTO.name = "CUSTOMER 001";
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(customerService.findById("000001")).thenReturn(customerDTO);

        mockMvc.perform(get("/api/customers/000001")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("000001"))
                .andExpect(jsonPath("$.name").value("CUSTOMER 001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(customerService.findById("999999"))
                .thenThrow(new CustomerNotFoundException("Customer not found with id: 999999"));

        mockMvc.perform(get("/api/customers/999999")
                                .accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Customer not found with id: 999999"));
    }

    @Test
    void testGetAllFound() throws Exception {
        when(customerService.findAll()).thenReturn(List.of(customerDTO));

        mockMvc.perform(get("/api/customers")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("000001"));
    }

    @Test
    void testGetAllNoContent() throws Exception {
        when(customerService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/customers")
                                .accept("application/json"))
                .andExpect(status().isNoContent());
    }

}
