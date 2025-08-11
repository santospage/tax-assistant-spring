package br.com.santospage.taxassistant.infrastructure.database;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
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
class FiscalMovementRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private FiscalMovementRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new FiscalMovementRepositoryImpl(jdbcTemplate);
    }

    @Test
    void testFindAll() {
        // Arrange
        FiscalMovement fm = new FiscalMovement();
        fm.setTaxCode("ICMS");
        List<FiscalMovement> mockResult = List.of(fm);

        when(jdbcTemplate.query(anyString(), any(FiscalMovementRowMapper.class)))
                .thenReturn(mockResult);

        // Act
        List<FiscalMovement> result = repository.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("ICMS", result.getFirst().getTaxCode());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(FiscalMovementRowMapper.class));
    }

    @Test
    void testFindByTableFound() {
        // Arrange
        FiscalMovement fm = new FiscalMovement();
        fm.setTaxCode("IPI");
        List<FiscalMovement> mockResult = List.of(fm);

        when(jdbcTemplate.query(anyString(), any(FiscalMovementRowMapper.class), eq("000001")))
                .thenReturn(mockResult);

        // Act
        List<FiscalMovement> result = repository.findByTable("000001");

        // Assert
        assertEquals(1, result.size());
        assertEquals("IPI", result.getFirst().getTaxCode());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(FiscalMovementRowMapper.class), eq("000001"));
    }

    @Test
    void testFindByTableNotFound() {
        // Arrange
        when(jdbcTemplate.query(anyString(), any(FiscalMovementRowMapper.class), eq("000002")))
                .thenReturn(List.of());

        // Act
        List<FiscalMovement> result = repository.findByTable("000002");

        // Assert
        assertTrue(result.isEmpty());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(FiscalMovementRowMapper.class), eq("000002"));
    }

    @Test
    void testFindByIdFound() {
        // Arrange
        FiscalMovement fm = new FiscalMovement();
        fm.setTaxCode("ISS");
        List<FiscalMovement> mockResult = List.of(fm);

        when(jdbcTemplate.query(anyString(), any(FiscalMovementRowMapper.class), eq("001")))
                .thenReturn(mockResult);

        // Act
        Optional<FiscalMovement> result = repository.findById("001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("ISS", result.get().getTaxCode());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(FiscalMovementRowMapper.class), eq("001"));
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(jdbcTemplate.query(anyString(), any(FiscalMovementRowMapper.class), eq("999")))
                .thenReturn(List.of());

        // Act
        Optional<FiscalMovement> result = repository.findById("999");

        // Assert
        assertTrue(result.isEmpty());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(FiscalMovementRowMapper.class), eq("999"));
    }
}
