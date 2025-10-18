package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.ProductModel;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void shouldReturnProductWhenExists() {
        ProductModel product = mock(ProductModel.class);
        when(product.getCompany()).thenReturn("01");
        when(product.getId()).thenReturn("000001");
        when(product.getName()).thenReturn("PRODUCT 001");
        when(product.isActive()).thenReturn(true);

        when(repository.findByCompanyAndId("01", "000001"))
                .thenReturn(Optional.of(product));

        ProductModel result = productService.findByCompanyAndId("01", "000001");

        assertNotNull(result);
        assertEquals("01", result.getCompany());
        assertEquals("000001", result.getId());
        assertEquals("PRODUCT 001", result.getName());

        verify(repository).findByCompanyAndId("01", "000001");
    }

    @Test
    void shouldThrowWhenProductNotExists() {
        // Given
        when(repository.findByCompanyAndId("01", "000099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.findByCompanyAndId("01", "000099")
        );

        verify(repository).findByCompanyAndId("01", "000099");
    }

    @Test
    void shouldReturnAllProducts() {
        // Given
        ProductModel product1 = mock(ProductModel.class);
        when(product1.isActive()).thenReturn(true);

        ProductModel product2 = mock(ProductModel.class);
        when(product2.isActive()).thenReturn(true);

        when(repository.findAll()).thenReturn(List.of(product1, product2));

        // When
        List<ProductModel> result = productService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(ProductModel::isActive));

        verify(repository).findAll();
    }

    @Test
    void shouldAllProductsNotExists() {
        // Given
        when(repository.findAll()).thenReturn(List.of());

        // When
        List<ProductModel> result = productService.findAll();

        // Then
        assertTrue(result.isEmpty());

        verify(repository).findAll();
    }
}