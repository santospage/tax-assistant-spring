package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.entities.Product;
import br.com.santospage.taxassistant.domain.exceptions.GlobalExceptionHandler;
import br.com.santospage.taxassistant.domain.exceptions.ProductNotFoundException;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import br.com.santospage.taxassistant.interfaces.controllers.CustomerController;
import br.com.santospage.taxassistant.interfaces.dtos.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        CustomerService customerService = mock(CustomerService.class);
        Object mockMvc = MockMvcBuilders
                .standaloneSetup(new CustomerController(customerService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnProductDtoWhenExists() {
        // Given
        Product entity = buildProduct("000001", "Notebook");
        when(repository.findById("000001")).thenReturn(Optional.of(entity));

        // When
        ProductDTO result = productService.findById("000001");

        // Then
        assertNotNull(result);
        assertEquals("Notebook", result.name);
        assertEquals("000001", result.id);
        verify(repository).findById("000001");
    }

    @Test
    void shouldReturnEmptyWhenProductNotExists() {
        // Given
        when(repository.findById("000099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ProductNotFoundException.class, () -> {
                    productService.findById("000099");
                }
        );

        verify(repository).findById("000099");
    }

    @Test
    void shouldReturnAllProductsAsDto() {
        // Given
        List<Product> products = List.of(
                buildProduct("000001", "Notebook"),
                buildProduct("000002", "Smartphone")
        );
        when(repository.findAll()).thenReturn(products);

        // When
        List<ProductDTO> result = productService.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("Notebook", result.get(0).name);
        assertEquals("Smartphone", result.get(1).name);
        verify(repository).findAll();
    }

    // Helper method to create a dummy product
    private Product buildProduct(String id, String name) {
        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setCompany("010101");
        p.setTypeProduct("PA");
        p.setSpecifingCodeST("2200100");
        p.setUnitMeasure("UN");
        p.setUnitValue(3500.00);
        p.setStandarOutflowCode("001");
        p.setStandarInflowCode("501");
        p.setMercosulExtNomenclature("38112140");
        return p;
    }
}
