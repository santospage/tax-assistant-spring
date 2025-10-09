package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.FiscalMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.FiscalMovement;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
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
class FiscalMovementServiceTest {

    @Mock
    private FiscalMovementRepository repository;

    @InjectMocks
    private FiscalMovementService service;

    @Test
    void shouldThrowWhenFiscalMovementNotExists() {
        // Given
        when(repository.findById("099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                FiscalMovementNotFoundException.class,
                () -> service.findById("099")
        );

        verify(repository).findById("099");
    }

    @Test
    void shouldReturnFiscalMovementsById() {
        // Given
        FiscalMovement fiscalMovement = new FiscalMovement();
        fiscalMovement.setCompanyCode("D RJ 02");
        fiscalMovement.setMovementId("001");

        when(repository.findById("001"))
                .thenReturn(Optional.of(fiscalMovement));

        // When
        FiscalMovement result = service.findById("001");

        // Then
        assertNotNull(result);
        assertEquals("D RJ 02", result.getCompanyCode());
        assertEquals("001", result.getMovementId());

        verify(repository).findById("001");
    }

    @Test
    void shouldReturnFiscalMovementsByTable() {
        // Given
        FiscalMovement fiscalMovement = new FiscalMovement();
        fiscalMovement.setCompanyCode("D RJ 02");
        fiscalMovement.setTable("TAB01");

        when(repository.findByTableMovement("TAB01"))
                .thenReturn(List.of(fiscalMovement));

        // When
        List<FiscalMovement> results = service.findByTableMovement("TAB01");

        // Then
        assertNotNull(results);
        assertEquals(1, results.size());

        FiscalMovement result = results.getFirst();
        assertEquals("D RJ 02", result.getCompanyCode());
        assertEquals("TAB01", result.getTable());

        verify(repository).findByTableMovement("TAB01");
    }

    @Test
    void shouldReturnAllFiscalMovements() {
        // Given
        FiscalMovement fm1 = new FiscalMovement();
        fm1.setCompanyCode("D RJ 02");
        fm1.setTable("TAB01");

        FiscalMovement fm2 = new FiscalMovement();
        fm2.setCompanyCode("D RJ 02");
        fm2.setTable("TAB02");

        List<FiscalMovement> list = List.of(fm1, fm2);
        when(repository.findAll()).thenReturn(list);

        // When
        List<FiscalMovement> result = service.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("TAB01", result.get(0).getTable());
        assertEquals("TAB02", result.get(1).getTable());
        assertEquals("D RJ 02", result.get(0).getCompanyCode());
        assertEquals("D RJ 02", result.get(1).getCompanyCode());

        verify(repository).findAll();
    }
}