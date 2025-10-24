package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.FiscalMovementModel;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
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
class FiscalMovementServiceTest {

    @Mock
    private FiscalMovementRepository repository;

    @InjectMocks
    private FiscalMovementService service;

    @Test
    void shouldReturnFiscalMovementsById() {
        // Given
        FiscalMovementModel fiscalMovement = mock(FiscalMovementModel.class);
        when(fiscalMovement.getCompanyCode()).thenReturn("01");
        when(fiscalMovement.getMovementId()).thenReturn("000001");
        when(fiscalMovement.isActive()).thenReturn(true);

        when(repository.findById("000001"))
                .thenReturn(Optional.of(fiscalMovement));

        // When
        FiscalMovementModel result = service.findById("000001");

        // Then
        assertNotNull(result);
        assertEquals("01", result.getCompanyCode());
        assertEquals("000001", result.getMovementId());
        verify(repository).findById("000001");
    }

    @Test
    void shouldThrowWhenFiscalMovementNotExists() {
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
    void shouldReturnFiscalMovementsByTable() {
        // Given
        FiscalMovementModel fiscalMovement = mock(FiscalMovementModel.class);
        when(fiscalMovement.getCompanyCode()).thenReturn("01");
        when(fiscalMovement.getMovementTable()).thenReturn("TAB01");
        when(fiscalMovement.isActive()).thenReturn(true);

        when(repository.findByMovementTable(eq("TAB01"), any(Sort.class)))
                .thenReturn(List.of(fiscalMovement));

        // When
        List<FiscalMovementModel> results = service.findByTableMovement("TAB01");

        // Then
        assertNotNull(results);
        assertEquals(1, results.size());

        FiscalMovementModel result = results.getFirst();
        assertEquals("01", result.getCompanyCode());
        assertEquals("TAB01", result.getMovementTable());

        verify(repository).findByMovementTable(eq("TAB01"), any(Sort.class));
    }

    @Test
    void shouldReturnFiscalMovementsByTableNoExists() {
        // Given
        when(repository.findByMovementTable(eq("099"), any(Sort.class)))
                .thenReturn(List.of());

        // When / Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> service.findByTableMovement("099")
        );
        
        verify(repository).findByMovementTable(eq("099"), any(Sort.class));
    }

    void shouldReturnAllFiscalMovements() {
        // Given
        FiscalMovementModel fm1 = mock(FiscalMovementModel.class);
        when(fm1.getCompanyCode()).thenReturn("01");
        when(fm1.getMovementTable()).thenReturn("TAB01");
        when(fm1.isActive()).thenReturn(true);

        FiscalMovementModel fm2 = mock(FiscalMovementModel.class);
        when(fm2.getCompanyCode()).thenReturn("01");
        when(fm2.getMovementTable()).thenReturn("TAB02");
        when(fm2.isActive()).thenReturn(true);

        List<FiscalMovementModel> list = List.of(fm1, fm2);
        when(repository.findAll()).thenReturn(list);

        // When
        List<FiscalMovementModel> result = service.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("01", result.get(0).getCompanyCode());
        assertEquals("TAB01", result.get(0).getMovementTable());
        assertEquals("01", result.get(1).getCompanyCode());
        assertEquals("TAB02", result.get(1).getMovementTable());

        verify(repository).findAll();
    }

    @Test
    void shouldAllFiscalMovementsNotExists() {
        // Given
        when(repository.findAll(any(Sort.class))).thenReturn(List.of());

        // When
        List<FiscalMovementModel> result = service.findAll();

        // Then
        assertTrue(result.isEmpty());

        verify(repository).findAll(any(Sort.class));
    }
}