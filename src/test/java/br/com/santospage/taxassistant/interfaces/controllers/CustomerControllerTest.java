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
        customer.setId("FIN001");
        customer.setName("CLIENTE PJ S/ IMPOSTO");
        customer.setNatureCustomer("          ");
        customer.setAddress("RUA                                     ");
        customer.setTypeCustomer("F");
        customer.setUfCustomer("SP");
        customer.setMunicipalCode("50308");
        customer.setCityCustomer("SAO PAULO                                                   ");
        customer.setNeighborhoodCustomer("                              ");
        customer.setZipCodeCustomer("        ");
        customer.setCountryCustomer("   ");
        customer.setPhoneCustomer("               ");
        customer.setNationalRegistryCustomer("17779772000176");
        customer.setStateRegistrationCustomer("                  ");
        customer.setCompany("D RJ    ");

        when(service.findByFilialAndId("D RJ", "FIN001")).thenReturn(customer);

        mockMvc.perform(get("/api/customers")
                                .param("company", "D RJ")
                                .param("id", "FIN001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("FIN001"))
                .andExpect(jsonPath("$.name").value("CLIENTE PJ S/ IMPOSTO"));
    }

    @Test
    void shouldGetByIdNotFound() throws Exception {
        when(service.findByFilialAndId("D RJ", "FIN999"))
                .thenThrow(new CustomerNotFoundException(""));

        mockMvc.perform(get("/api/customers")
                                .param("company", "D RJ")
                                .param("id", "FIN999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllSuccess() throws Exception {
        Customer customer1 = new Customer();
        customer1.setId("FIN001");
        customer1.setName("Cliente 1");
        customer1.setNatureCustomer("          ");
        customer1.setAddress("RUA                                     ");
        customer1.setTypeCustomer("F");
        customer1.setUfCustomer("SP");
        customer1.setMunicipalCode("50308");
        customer1.setCityCustomer("SAO PAULO                                                   ");
        customer1.setNeighborhoodCustomer("                              ");
        customer1.setZipCodeCustomer("        ");
        customer1.setCountryCustomer("   ");
        customer1.setPhoneCustomer("               ");
        customer1.setNationalRegistryCustomer("17779772000176");
        customer1.setStateRegistrationCustomer("                  ");
        customer1.setCompany("D RJ    ");

        Customer customer2 = new Customer();
        customer2.setId("FIN002");
        customer2.setName("Cliente 2");
        customer2.setNatureCustomer("          ");
        customer2.setAddress("RUA                                     ");
        customer2.setTypeCustomer("F");
        customer2.setUfCustomer("SP");
        customer2.setMunicipalCode("50308");
        customer2.setCityCustomer("SAO PAULO                                                   ");
        customer2.setNeighborhoodCustomer("                              ");
        customer2.setZipCodeCustomer("        ");
        customer2.setCountryCustomer("   ");
        customer2.setPhoneCustomer("               ");
        customer2.setNationalRegistryCustomer("17779772000176");
        customer2.setStateRegistrationCustomer("                  ");
        customer2.setCompany("D RJ    ");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        // Mock
        when(service.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/customers")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("FIN001"))
                .andExpect(jsonPath("$[0].name").value("Cliente 1"))
                .andExpect(jsonPath("$[1].id").value("FIN002"))
                .andExpect(jsonPath("$[1].name").value("Cliente 2"));
    }

    @Test
    void shouldGetAllNoContent() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/customers")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}