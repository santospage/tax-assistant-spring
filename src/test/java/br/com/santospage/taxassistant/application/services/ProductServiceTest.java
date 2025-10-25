package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.ProductModel;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

        ProductModel result = productService.findByCompanyAndId(
                "01"
                , "000001"
        );

        assertNotNull(result);
        assertEquals("01", result.getCompanyCode());
        assertEquals("000001", result.getProductId());
        assertEquals("PRODUCT 001", result.getNameProduct());

        verify(repository).findByCompanyCodeAndProductId("01", "000001");
    }

    @Test
    void shouldThrowWhenProductNotExists() {
        // Given
        when(repository.findByCompanyCodeAndProductId("01", "000099"))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.findByCompanyAndId("01", "000099")
        );

        verify(repository).findByCompanyCodeAndProductId("01", "000099");
    }

    void shouldReturnAllProducts() {
        // Given
        ProductModel product1 = mock(ProductModel.class);
        when(product1.isActive()).thenReturn(true);

        ProductModel product2 = mock(ProductModel.class);
        when(product2.isActive()).thenReturn(true);

        List<ProductModel> products = List.of(product1, product2);
        Page<ProductModel> page = new PageImpl<>(products);

        // Mock
        when(repository.findAll(any(Pageable.class))).thenReturn(page);

        // When
        Page<ProductModel> result = productService.findAll(1, 10);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertTrue(result.getContent().stream().allMatch(ProductModel::isActive));

        verify(repository).findAll(any(Pageable.class));
    }

    @Test
    void shouldAllProductsNotExists() {
        List<ProductModel> products = List.of();
        Page<ProductModel> page = new PageImpl<>(products);

        // Given
        when(repository.findAll(any(Pageable.class))).thenReturn(page);

        // When
        Page<ProductModel> result = productService.findAll(1, 10);

        // Then
        assertTrue(result.isEmpty());

        verify(repository).findAll(any(Pageable.class));
    }
}