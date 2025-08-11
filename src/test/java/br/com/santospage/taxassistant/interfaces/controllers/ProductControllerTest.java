package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.interfaces.dtos.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDTO productDTO;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        productDTO = new ProductDTO();
        productDTO.id = "LJTEST01";
        productDTO.name = "PRODUCT 001";
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(productService.findById("LJTEST01")).thenReturn(Optional.of(productDTO));

        mockMvc.perform(get("/api/products/LJTEST01")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("LJTEST01"))
                .andExpect(jsonPath("$.name").value("PRODUCT 001"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(productService.findById("NOTFOUND")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/NOTFOUND")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllWithResults() throws Exception {
        when(productService.findAll()).thenReturn(List.of(productDTO));

        mockMvc.perform(get("/api/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("LJTEST01"))
                .andExpect(jsonPath("$[0].name").value("PRODUCT 001"));
    }

    @Test
    void testGetAllNoContent() throws Exception {
        when(productService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

