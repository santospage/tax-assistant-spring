package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.IntegratedMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.IntegratedMovement;
import br.com.santospage.taxassistant.domain.repositories.IntegratedMovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IntegratedMovementServiceTest {

    private IntegratedMovementRepository repository;
    private IntegratedMovementService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IntegratedMovementRepository.class);
        service = new IntegratedMovementService(repository);
    }

    @Test
    void getAll_shouldReturnList_whenRepositoryHasData() {
        // Arrange
        IntegratedMovement movement = new IntegratedMovement(null, null, null);
        when(repository.findIntegratedMovements(null)).thenReturn(List.of(movement));

        // Act
        List<IntegratedMovement> result = service.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findIntegratedMovements(null);
    }

    @Test
    void getAll_shouldThrowException_whenRepositoryReturnsEmptyList() {
        // Arrange
        when(repository.findIntegratedMovements(null)).thenReturn(Collections.emptyList());

        // Act & Assert
        IntegratedMovementNotFoundException exception = assertThrows(
                IntegratedMovementNotFoundException.class,
                () -> service.getAll()
        );

        assertEquals("No records found.", exception.getMessage());
        verify(repository, times(1)).findIntegratedMovements(null);
    }

    @Test
    void getByCompany_shouldReturnList_whenRepositoryHasData() {
        // Arrange
        String company = "ABC";
        IntegratedMovement movement = new IntegratedMovement(null, null, null);
        when(repository.findIntegratedMovements(company)).thenReturn(List.of(movement));

        // Act
        List<IntegratedMovement> result = service.getByCompany(company);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findIntegratedMovements(company);
    }

    @Test
    void getByCompany_shouldThrowException_whenRepositoryReturnsEmptyList() {
        // Arrange
        String company = "XYZ";
        when(repository.findIntegratedMovements(company)).thenReturn(Collections.emptyList());

        // Act & Assert
        IntegratedMovementNotFoundException exception = assertThrows(
                IntegratedMovementNotFoundException.class,
                () -> service.getByCompany(company)
        );

        assertEquals("No records found for the company: XYZ", exception.getMessage());
        verify(repository, times(1)).findIntegratedMovements(company);
    }
}
