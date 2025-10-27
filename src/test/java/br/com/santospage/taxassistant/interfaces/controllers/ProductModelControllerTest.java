package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.ProductModel;
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

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetByIdFound() throws Exception {
        ProductModel product = mock(ProductModel.class);
        when(product.getCompanyCode()).thenReturn("01");
        when(product.getProductId()).thenReturn("000001");
        when(product.getNameProduct()).thenReturn("PRODUCT 001");

        when(service.findByCompanyAndId("01", "000001")).thenReturn(product);

        mockMvc.perform(get("/api/products")
                                .param("company", "01")
                                .param("id", "000001")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("000001"))
                .andExpect(jsonPath("$.nameProduct").value("PRODUCT 001"));
    }

    @Test
    void shouldGetByIdNotFound() throws Exception {
        when(service.findByCompanyAndId("01", "TEST999"))
                .thenThrow(new ResourceNotFoundException("Product not found: TEST999"));

        mockMvc.perform(get("/api/products")
                                .param("company", "01")
                                .param("id", "TEST999")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllSuccess() throws Exception {
        ProductModel product1 = mock(ProductModel.class);
        when(product1.getProductId()).thenReturn("000001");
        when(product1.getNameProduct()).thenReturn("PRODUCT 001");

        ProductModel product2 = mock(ProductModel.class);
        when(product2.getProductId()).thenReturn("000002");
        when(product2.getNameProduct()).thenReturn("PRODUCT 002");

        List<ProductModel> list = List.of(product1, product2);
        Page<ProductModel> page = new PageImpl<>(list, PageRequest.of(0, 10), list.size());

        // Mock
        when(service.findAll(any(), any())).thenReturn(page);

        mockMvc.perform(get("/api/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value("000001"))
                .andExpect(jsonPath("$[0].nameProduct").value("PRODUCT 001"))
                .andExpect(jsonPath("$[1].productId").value("000002"))
                .andExpect(jsonPath("$[1].nameProduct").value("PRODUCT 002"));
    }

    @Test
    void shouldGetAllNoContent() throws Exception {
        PageImpl<ProductModel> emptyPage = new PageImpl<>(
                Collections.emptyList(),
                PageRequest.of(0, 10),
                0
        );

        when(service.findAll(any(), any())).thenReturn(emptyPage);

        mockMvc.perform(get("/api/products")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}