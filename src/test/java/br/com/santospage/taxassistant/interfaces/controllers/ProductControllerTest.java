package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.domain.exceptions.ProductNotFoundException;
import br.com.santospage.taxassistant.domain.models.Product;
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
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    void shouldGetByIdFound() throws Exception {
        Product product = new Product();
        product.setCompany("01");
        product.setId("TEST001");
        product.setName("PRODUCT TEST001");

        when(service.findByFilialAndId("01", "TEST001")).thenReturn(product);

        mockMvc.perform(get("/api/products")
                                .param("company", "01")
                                .param("id", "TEST001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("TEST001"))
                .andExpect(jsonPath("$.name").value("PRODUCT TEST001"));
    }

    @Test
    void shouldGetByIdNotFound() throws Exception {
        when(service.findByFilialAndId("01", "TEST999"))
                .thenThrow(new ProductNotFoundException(""));

        mockMvc.perform(get("/api/products")
                                .param("company", "01")
                                .param("id", "TEST999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllSuccess() throws Exception {
        Product product1 = new Product();
        product1.setCompany("01");
        product1.setId("TEST001");
        product1.setName("PRODUCT TEST001");

        Product product2 = new Product();
        product2.setCompany("01");
        product2.setId("TEST002");
        product2.setName("PRODUCT TEST002");

        List<Product> products = Arrays.asList(product1, product2);

        // Mock
        when(service.findAll()).thenReturn(products);

        mockMvc.perform(get("/api/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("TEST001"))
                .andExpect(jsonPath("$[0].name").value("PRODUCT TEST001"))
                .andExpect(jsonPath("$[1].id").value("TEST002"))
                .andExpect(jsonPath("$[1].name").value("PRODUCT TEST002"));
    }

    @Test
    void shouldGetAllNoContent() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}