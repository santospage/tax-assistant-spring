package br.com.santospage.taxassistant.infrastructure.persistence;

import br.com.santospage.taxassistant.domain.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private ProductRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new ProductRepositoryImpl(jdbcTemplate);
    }

    @Test
    void testFindAll() {
        // Arrange
        Product product = new Product();
        product.setName("Laptop");
        List<Product> mockResult = List.of(product);

        when(jdbcTemplate.query(anyString(), any(ProductRowMapper.class)))
                .thenReturn(mockResult);

        // Act
        List<Product> result = repository.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Laptop", result.getFirst().getName());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(ProductRowMapper.class));
    }

    @Test
    void testFindByIdFound() {
        // Arrange
        Product product = new Product();
        product.setName("Smartphone");
        List<Product> mockResult = List.of(product);

        when(jdbcTemplate.query(anyString(), any(ProductRowMapper.class), eq("000001")))
                .thenReturn(mockResult);

        // Act
        Optional<Product> result = repository.findById("000001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Smartphone", result.get().getName());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(ProductRowMapper.class), eq("000001"));
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(jdbcTemplate.query(anyString(), any(ProductRowMapper.class), eq("000999")))
                .thenReturn(List.of());

        // Act
        Optional<Product> result = repository.findById("000999");

        // Assert
        assertTrue(result.isEmpty());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(ProductRowMapper.class), eq("000999"));
    }
}

