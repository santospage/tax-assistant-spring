/*
package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.domain.exceptions.ProductNotFoundException;
import br.com.santospage.taxassistant.domain.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        product1 = new Product();
        product1.setId("P001");
        product1.setName("Product 001");

        product2 = new Product();
        product2.setId("P002");
        product2.setName("Product 002");
    }

    @Test
    void testGetProductByIdFound() throws Exception {
        when(productService.findByFilialAndId("D RJ 02", "P001")).thenReturn(product1);

        mockMvc.perform(get("/api/products/{id}", "P001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("P001"))
                .andExpect(jsonPath("$.name").value("Product 001"))
                .andExpect(jsonPath("$.category").value("Category A"));

        verify(productService).findByFilialAndId("D RJ 02", "P001");
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {
        when(productService.findByFilialAndId("D RJ 02", "P999"))
                .thenThrow(new ProductNotFoundException("Product not found with ID: P999"));

        mockMvc.perform(get("/api/products/{id}", "P999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(productService).findByFilialAndId("D RJ 02", "P999");
    }

    @Test
    void testGetAllProductsFound() throws Exception {
        List<Product> productList = List.of(product1, product2);
        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/api/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("P001"))
                .andExpect(jsonPath("$[0].name").value("Product 001"))
                .andExpect(jsonPath("$[0].category").value("Category A"))
                .andExpect(jsonPath("$[1].id").value("P002"))
                .andExpect(jsonPath("$[1].name").value("Product 002"))
                .andExpect(jsonPath("$[1].category").value("Category B"));

        verify(productService).findAll();
    }

    @Test
    void testGetAllProductsNotFound() throws Exception {
        when(productService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(productService).findAll();
    }
}
*/