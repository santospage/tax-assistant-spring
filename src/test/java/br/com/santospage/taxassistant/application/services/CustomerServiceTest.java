package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.CustomerNotFoundException;
import br.com.santospage.taxassistant.domain.models.Customer;
import br.com.santospage.taxassistant.domain.repositories.CustomerRepository;
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
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    // Since the constructor is private, we use reflection via @InjectMocks to inject the mock
    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldThrowWhenCustomerNotExists() {
        // Given
        when(repository.findByFilialAndId("D RJ", "000099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.findByFilialAndId("D RJ", "000099")
        );

        verify(repository).findByFilialAndId("D RJ", "000099");
    }

    @Test
    void shouldReturnCustomerWhenExists() {
        // Given
        Customer customer = new Customer();
        customer.setCompany("D RJ");
        customer.setId("000001");
        customer.setName("CUSTOMER 001");

        when(repository.findByFilialAndId("D RJ", "000001"))
                .thenReturn(Optional.of(customer));

        // When
        Customer result = customerService.findByFilialAndId("D RJ", "000001");

        // Then
        assertNotNull(result);
        assertEquals("D RJ", result.getCompany());
        assertEquals("000001", result.getId());
        assertEquals("CUSTOMER 001", result.getName());

        verify(repository).findByFilialAndId("D RJ", "000001");
    }

    @Test
    void shouldReturnAllCustomers() {
        // Given
        Customer c1 = new Customer();
        c1.setId("000001");
        c1.setName("CUSTOMER 001");
        c1.setCompany("D RJ");

        Customer c2 = new Customer();
        c2.setId("000002");
        c2.setName("CUSTOMER 002");
        c2.setCompany("D RJ");

        List<Customer> customers = List.of(c1, c2);
        when(repository.findAll()).thenReturn(customers);

        // When
        List<Customer> result = customerService.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("000001", result.getFirst().getId());
        assertEquals("CUSTOMER 001", result.get(0).getName());
        assertEquals("D RJ", result.get(0).getCompany());

        assertEquals("000002", result.get(1).getId());
        assertEquals("CUSTOMER 002", result.get(1).getName());
        assertEquals("D RJ", result.get(1).getCompany());

        verify(repository).findAll();
    }
}