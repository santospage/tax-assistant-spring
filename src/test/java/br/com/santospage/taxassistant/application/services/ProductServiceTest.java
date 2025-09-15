package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ProductNotFoundException;
import br.com.santospage.taxassistant.domain.models.Product;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldThrowWhenProductNotExists() {
        // Given
        when(repository.findByFilialAndId("D RJ 02", "000001")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ProductNotFoundException.class,
                () -> productService.findByFilialAndId("D RJ 02", "000001")
        );

        verify(repository).findByFilialAndId("D RJ 02", "000001");
    }

    @Test
    void shouldReturnProductWhenExists() {
        // Given
        Product product = new Product();
        product.setCompany("D RJ 02");
        product.setId("000001");
        product.setName("NOTEBOOK");

        when(repository.findByFilialAndId("D RJ 02", "000001"))
                .thenReturn(Optional.of(product));

        // When
        Product result = productService.findByFilialAndId("D RJ 02", "000001");

        // Then
        assertNotNull(result);
        assertEquals("D RJ 02", result.getCompany());
        assertEquals("000001", result.getId());
        assertEquals("NOTEBOOK", result.getName());

        verify(repository).findByFilialAndId("D RJ 02", "000001");
    }

    @Test
    void shouldReturnAllProducts() {
        // Given
        Product p1 = new Product();
        p1.setId("P001");
        p1.setName("Product 001");

        Product p2 = new Product();
        p2.setId("P002");
        p2.setName("Product 002");

        List<Product> products = List.of(p1, p2);
        when(repository.findAll()).thenReturn(products);

        // When
        List<Product> result = productService.findAll();

        // Then
        assertEquals(2, result.size());

        assertEquals("P001", result.get(0).getId());
        assertEquals("Product 001", result.get(0).getName());

        assertEquals("P002", result.get(1).getId());
        assertEquals("Product 002", result.get(1).getName());

        verify(repository).findAll();
    }
}