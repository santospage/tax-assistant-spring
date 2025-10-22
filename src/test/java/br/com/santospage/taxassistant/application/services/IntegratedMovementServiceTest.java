package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.IntegratedMovement;
import br.com.santospage.taxassistant.domain.repositories.IntegratedMovementRepository;
import br.com.santospage.taxassistant.interfaces.dto.IntegratedMovementDTO;
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
        IntegratedMovement movement = new IntegratedMovement(
                "001",           // companyCode
                "TX123",         // taxId
                "Description 1"  // descriptionTax
        );
        when(repository.findIntegratedMovements(null)).thenReturn(List.of(movement));

        // Act
        List<IntegratedMovementDTO> result = service.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        IntegratedMovementDTO dto = result.getFirst();
        assertEquals("001", dto.companyCode());
        assertEquals("TX123", dto.taxId());
        assertEquals("Description 1", dto.descriptionTax());

        verify(repository, times(1)).findIntegratedMovements(null);
    }


    @Test
    void getAll_shouldThrowException_whenRepositoryReturnsEmptyList() {
        // Arrange
        when(repository.findIntegratedMovements(null)).thenReturn(Collections.emptyList());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.getAll()
        );

        assertEquals("No records found.", exception.getMessage());
        verify(repository, times(1)).findIntegratedMovements(null);
    }

    @Test
    void getByCompany_shouldReturnList_whenRepositoryHasData() {
        // Arrange
        String company = "ABC";
        IntegratedMovement movement = new IntegratedMovement(
                "ABC",
                "TX456",
                "Description 2"
        );
        when(repository.findIntegratedMovements(company)).thenReturn(List.of(movement));

        // Act
        List<IntegratedMovementDTO> result = service.getByCompany(company);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        IntegratedMovementDTO dto = result.getFirst();
        assertEquals("ABC", dto.companyCode());
        assertEquals("TX456", dto.taxId());
        assertEquals("Description 2", dto.descriptionTax());

        verify(repository, times(1)).findIntegratedMovements(company);
    }


    @Test
    void getByCompany_shouldThrowException_whenRepositoryReturnsEmptyList() {
        // Arrange
        String company = "XYZ";
        when(repository.findIntegratedMovements(company)).thenReturn(Collections.emptyList());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.getByCompany(company)
        );

        assertEquals("No records found for the company: XYZ", exception.getMessage());
        verify(repository, times(1)).findIntegratedMovements(company);
    }
}
