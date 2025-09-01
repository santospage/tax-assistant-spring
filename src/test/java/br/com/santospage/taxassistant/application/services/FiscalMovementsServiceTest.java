package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.domain.exceptions.FiscalMovementNotFoundException;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
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
class FiscalMovementsServiceTest {

    @Mock
    private FiscalMovementRepository repository;

    @InjectMocks
    private FiscalMovementsService service;

    @Test
    void shouldReturnFiscalMovementDtoWhenExists() {
        // Given
        FiscalMovement entity = buildFiscalMovement("001", "TAB01");
        when(repository.findById("001")).thenReturn(Optional.of(entity));

        // When
        FiscalMovementDTO result = service.findById("001");

        // Then
        assertNotNull(result);
        assertEquals("001", result.idMovement);
        assertEquals("TAB01", result.tableMovement);
        verify(repository).findById("001");
    }

    @Test
    void shouldReturnEmptyWhenFiscalMovementNotExists() {
        // Given
        when(repository.findById("099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                FiscalMovementNotFoundException.class, () -> {
                    service.findById("099");
                }
        );

        verify(repository).findById("099");
    }

    @Test
    void shouldReturnFiscalMovementsByTable() {
        // Given
        List<FiscalMovement> list = List.of(
                buildFiscalMovement("001", "TAB01"),
                buildFiscalMovement("002", "TAB01")
        );
        when(repository.findByTable("TAB01")).thenReturn(list);

        // When
        List<FiscalMovementDTO> result = service.findByTable("TAB01");

        // Then
        assertEquals(2, result.size());
        assertEquals("TAB01", result.get(0).tableMovement);
        assertEquals("TAB01", result.get(1).tableMovement);
        verify(repository).findByTable("TAB01");
    }

    @Test
    void shouldReturnAllFiscalMovements() {
        // Given
        List<FiscalMovement> list = List.of(
                buildFiscalMovement("001", "TAB01"),
                buildFiscalMovement("002", "TAB02")
        );
        when(repository.findAll()).thenReturn(list);

        // When
        List<FiscalMovementDTO> result = service.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("TAB01", result.get(0).tableMovement);
        assertEquals("TAB02", result.get(1).tableMovement);
        verify(repository).findAll();
    }

    // Helper method to create a dummy FiscalMovement
    private FiscalMovement buildFiscalMovement(String id, String table) {
        FiscalMovement f = new FiscalMovement();
        f.setMovementId(id);
        f.setTable(table);
        f.setCompanyCode("010101");
        f.setTaxCode("TX01");
        f.setTaxBase(1000.0);
        f.setTaxQuantity(10.0);
        f.setTaxAliquot(5.0);
        f.setTaxValue(50.0);
        f.setTaxValueMargin(10.0);
        f.setTaxTariffValue(2.0);
        f.setTaxTributeAumented(String.valueOf(3.0));
        f.setTaxAliquotAumented(1.00);
        f.setTaxValueAumented(4.00);
        return f;
    }
}
