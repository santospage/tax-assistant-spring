package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.ProductModel;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

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
        when(product.getCompanyCode()).thenReturn("01");
        when(product.getProductId()).thenReturn("000001");
        when(product.getNameProduct()).thenReturn("PRODUCT 001");
        when(product.isActive()).thenReturn(true);

        when(repository.findByCompanyCodeAndProductId("01", "000001"))
                .thenReturn(Optional.of(product));

        ProductModel result = productService.findByCompanyAndId("01", "000001");

        assertNotNull(result);
        assertEquals("01", result.getCompanyCode());
        assertEquals("000001", result.getProductId());
        assertEquals("PRODUCT 001", result.getNameProduct());

        verify(repository).findByCompanyCodeAndProductId("01", "000001");
    }

    @Test
    void shouldThrowWhenProductNotExists() {
        // Given
        when(repository.findByCompanyCodeAndProductId("01", "000099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.findByCompanyAndId("01", "000099")
        );

        verify(repository).findByCompanyCodeAndProductId("01", "000099");
    }

    @Test
    void shouldReturnAllProducts() {
        // Given
        ProductModel product1 = mock(ProductModel.class);
        when(product1.isActive()).thenReturn(true);

        ProductModel product2 = mock(ProductModel.class);
        when(product2.isActive()).thenReturn(true);

        when(repository.findAll(any(Sort.class))).thenReturn(List.of(product1, product2));

        // When
        List<ProductModel> result = productService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(ProductModel::isActive));

        verify(repository).findAll(any(Sort.class));
    }

    @Test
    void shouldAllProductsNotExists() {
        // Given
        when(repository.findAll(any(Sort.class))).thenReturn(List.of());

        // When
        List<ProductModel> result = productService.findAll();

        // Then
        assertTrue(result.isEmpty());

        verify(repository).findAll(any(Sort.class));
    }
}