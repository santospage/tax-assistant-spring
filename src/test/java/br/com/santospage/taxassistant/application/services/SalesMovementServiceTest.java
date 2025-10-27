package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.SalesMovementModel;
import br.com.santospage.taxassistant.domain.repositories.SalesMovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalesMovementServiceTest {
    @Mock
    private SalesMovementRepository repository;

    @InjectMocks
    private SalesMovementService service;

    @Test
    void shouldReturnSalesMovementsById() {
        // Given
        SalesMovementModel salesMovement = mock(SalesMovementModel.class);
        when(salesMovement.getCompanyCode()).thenReturn("01");
        when(salesMovement.getTaxId()).thenReturn("000001");
        when(salesMovement.isActive()).thenReturn(true);

        when(repository.findById("000001"))
                .thenReturn(Optional.of(salesMovement));

        // When
        SalesMovementModel result = service.findById("000001");

        // Then
        assertNotNull(result);
        assertEquals("01", result.getCompanyCode());
        assertEquals("000001", result.getTaxId());
        verify(repository).findById("000001");
    }

    @Test
    void shouldThrowWhenSalesMovementNotExists() {
        // Given
        when(repository.findById("099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> service.findById("099")
        );

        verify(repository).findById("099");
    }

    @Test
    void shouldReturnSalesMovementsByCustomer() {
        // Given
        SalesMovementModel salesMovement = mock(SalesMovementModel.class);
        when(salesMovement.getCompanyCode()).thenReturn("01");
        when(salesMovement.getCustomerCode()).thenReturn("CUSTOMER01");
        when(salesMovement.isActive()).thenReturn(true);

        when(repository.findByCustomerCode(eq("CUSTOMER01"), any(Sort.class)))
                .thenReturn(List.of(salesMovement));

        // When
        List<SalesMovementModel> results = service.findByCustomer("CUSTOMER01");

        // Then
        assertNotNull(results);
        assertEquals(1, results.size());

        SalesMovementModel result = results.getFirst();
        assertEquals("01", result.getCompanyCode());
        assertEquals("CUSTOMER01", result.getCustomerCode());

        verify(repository).findByCustomerCode(eq("CUSTOMER01"), any(Sort.class));
    }

    @Test
    void shouldReturnSalesMovementsByCustomerNotExists() {
        // Given
        when(repository.findByCustomerCode(eq("099"), any(Sort.class)))
                .thenReturn(List.of());

        // When / Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> service.findByCustomer("099")
        );

        verify(repository).findByCustomerCode(eq("099"), any(Sort.class));
    }

    @Test
    void shouldReturnSalesMovementsByProduct() {
        // Given
        SalesMovementModel salesMovement = mock(SalesMovementModel.class);
        when(salesMovement.getCompanyCode()).thenReturn("01");
        when(salesMovement.getProductCode()).thenReturn("PRODUCT01");
        when(salesMovement.isActive()).thenReturn(true);

        when(repository.findByProductCode(eq("PRODUCT01"), any(Sort.class)))
                .thenReturn(List.of(salesMovement));

        // When
        List<SalesMovementModel> results = service.findByProduct("PRODUCT01");

        // Then
        assertNotNull(results);
        assertEquals(1, results.size());

        SalesMovementModel result = results.getFirst();
        assertEquals("01", result.getCompanyCode());
        assertEquals("PRODUCT01", result.getProductCode());

        verify(repository).findByProductCode(eq("PRODUCT01"), any(Sort.class));
    }

    @Test
    void shouldReturnSalesMovementsByProductNotExists() {
        // Given
        when(repository.findByProductCode(eq("099"), any(Sort.class)))
                .thenReturn(List.of());

        // When / Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> service.findByProduct("099")
        );

        verify(repository).findByProductCode(eq("099"), any(Sort.class));
    }

    void shouldReturnAllSalesMovements() {
        // Given
        SalesMovementModel movement1 = mock(SalesMovementModel.class);
        when(movement1.isActive()).thenReturn(true);

        SalesMovementModel movement2 = mock(SalesMovementModel.class);
        when(movement2.isActive()).thenReturn(true);

        List<SalesMovementModel> movements = List.of(movement1, movement2);
        Page<SalesMovementModel> page = new PageImpl<>(movements);

        // Mock
        when(repository.findAll(any(Pageable.class))).thenReturn(page);

        // When
        Page<SalesMovementModel> result = service.findAll(1, 10);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertTrue(result.getContent().stream().allMatch(SalesMovementModel::isActive));

        verify(repository).findAll(any(Pageable.class));
    }

    @Test
    void shouldAllSalesMovementsNotExists() {
        List<SalesMovementModel> movements = List.of();
        Page<SalesMovementModel> page = new PageImpl<>(movements);

        // Given
        when(repository.findAll(any(Pageable.class))).thenReturn(page);

        // When
        Page<SalesMovementModel> result = service.findAll(1, 10);

        // Then
        assertTrue(result.isEmpty());

        verify(repository).findAll(any(Pageable.class));
    }
}
