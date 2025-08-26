package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.domain.exceptions.GlobalExceptionHandler;
import br.com.santospage.taxassistant.domain.exceptions.ProductNotFoundException;
import br.com.santospage.taxassistant.interfaces.dtos.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
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
    private ProductDTO productDTO;

    @BeforeEach
    void setup() {
        productService = mock(ProductService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductController(productService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        productDTO = new ProductDTO();
        productDTO.id = "000001";
        productDTO.name = "Notebook";
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(productService.findById("000001")).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/000001")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("000001"))
                .andExpect(jsonPath("$.name").value("Notebook"));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(productService.findById("NOTFOUND"))
                .thenThrow(new ProductNotFoundException("Product not found with id: NOTFOUND"));

        mockMvc.perform(get("/api/products/NOTFOUND")
                                .accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product not found with id: NOTFOUND"));
    }

    @Test
    void testGetAllFound() throws Exception {
        when(productService.findAll()).thenReturn(List.of(productDTO));

        mockMvc.perform(get("/api/products")
                                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("000001"));
    }

    @Test
    void testGetAllNoContent() throws Exception {
        when(productService.findAll()).thenThrow(new ProductNotFoundException("No products found"));

        mockMvc.perform(get("/api/products")
                                .accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No products found"));
    }
}

