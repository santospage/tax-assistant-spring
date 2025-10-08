package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.SalesMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.SalesMovement;
import br.com.santospage.taxassistant.domain.repositories.SalesMovementsRepository;
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
public class SalesMovementsServiceTest {
    @Mock
    private SalesMovementsRepository repository;

    @InjectMocks
    private SalesMovementsService service;

    @Test
    void shouldThrowWhenSalesMovementNotExists() {
        // Given
        when(repository.findById("099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                SalesMovementNotFoundException.class,
                () -> service.findById("099")
        );

        verify(repository).findById("099");
    }

    @Test
    void shouldReturnSalesMovementsById() {
        // Given
        SalesMovement salesMovement = new SalesMovement();
        salesMovement.setCompanyCode("COMPANY01");
        salesMovement.setIdTax("TEST001");

        when(repository.findById("TEST001"))
                .thenReturn(Optional.of(salesMovement));

        // When
        SalesMovement result = service.findById("TEST001");

        // Then
        assertNotNull(result);
        assertEquals("COMPANY01", result.getCompanyCode());
        assertEquals("TEST001", result.getIdTax());

        verify(repository).findById("TEST001");
    }

    @Test
    void shouldReturnSalesMovementsByCustomer() {
        // Given
        SalesMovement salesMovement = new SalesMovement();
        salesMovement.setCompanyCode("COMPANY01");
        salesMovement.setCustomerCode("TEST01");

        when(repository.findByCustomerCode("TEST01"))
                .thenReturn(List.of(salesMovement));

        // When
        List<SalesMovement> results = service.findByCustomer("TEST01");

        // Then
        assertNotNull(results);
        assertEquals(1, results.size());

        SalesMovement result = results.getFirst();
        assertEquals("COMPANY01", result.getCompanyCode());
        assertEquals("TEST01", result.getCustomerCode());

        verify(repository).findByCustomerCode("TEST01");
    }

    @Test
    void shouldReturnSalesMovementsByProduct() {
        // Given
        SalesMovement salesMovement = new SalesMovement();
        salesMovement.setCompanyCode("COMPANY01");
        salesMovement.setProductCode("PRODUCT01");

        when(repository.findByProductCode("PRODUCT01"))
                .thenReturn(List.of(salesMovement));

        // When
        List<SalesMovement> results = service.findByProduct("PRODUCT01");

        // Then
        assertNotNull(results);
        assertEquals(1, results.size());

        SalesMovement result = results.getFirst();
        assertEquals("COMPANY01", result.getCompanyCode());
        assertEquals("PRODUCT01", result.getProductCode());

        verify(repository).findByProductCode("PRODUCT01");
    }

    @Test
    void shouldReturnAllSalesMovements() {
        // Given
        SalesMovement sm1 = new SalesMovement();
        sm1.setCompanyCode("COMPANY01");
        sm1.setIdTax("ID001");

        SalesMovement sm2 = new SalesMovement();
        sm2.setCompanyCode("COMPANY01");
        sm2.setIdTax("ID002");

        List<SalesMovement> list = List.of(sm1, sm2);
        when(repository.findAll()).thenReturn(list);

        // When
        List<SalesMovement> result = service.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("COMPANY01", result.get(0).getCompanyCode());
        assertEquals("ID001", result.get(0).getIdTax());
        assertEquals("COMPANY01", result.get(1).getCompanyCode());
        assertEquals("ID002", result.get(1).getIdTax());
        verify(repository).findAll();
    }
}
