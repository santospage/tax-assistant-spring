package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.entities.Customer;
import br.com.santospage.taxassistant.domain.repositories.CustomerRepository;
import br.com.santospage.taxassistant.interfaces.dtos.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void shouldReturnCustomerDtoWhenExists() {
        // Given
        Customer entity = buildCustomer("000001", "CUSTOMER 001");
        when(repository.findById("000001")).thenReturn(Optional.of(entity));

        // When
        Optional<CustomerDTO> result = customerService.findById("000001");

        // Then
        assertTrue(result.isPresent());
        assertEquals("CUSTOMER 001", result.get().name);
        assertEquals("000001", result.get().id);
        verify(repository).findById("000001");
    }

    @Test
    void shouldReturnEmptyWhenCustomerNotExists() {
        // Given
        when(repository.findById("000099")).thenReturn(Optional.empty());

        // When
        Optional<CustomerDTO> result = customerService.findById("000099");

        // Then
        assertTrue(result.isEmpty());
        verify(repository).findById("000099");
    }

    @Test
    void shouldReturnAllCustomersAsDto() {
        // Given
        List<Customer> customers = List.of(
                buildCustomer("000001", "CUSTOMER 001"),
                buildCustomer("000002", "CUSTOMER 002")
        );
        when(repository.findAll()).thenReturn(customers);

        // When
        List<CustomerDTO> result = customerService.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("CUSTOMER 001", result.get(0).name);
        assertEquals("CUSTOMER 002", result.get(1).name);
        verify(repository).findAll();
    }

    // Helper method to create a dummy customer
    private Customer buildCustomer(String id, String name) {
        Customer c = new Customer();
        c.setId(id);
        c.setName(name);
        c.setCompany("010101");
        c.setNatureCustomer("J");
        c.setAddress("RUA 9 DE JULHO, 123");
        c.setTypeCustomer("F");
        c.setUfCustomer("SP");
        c.setMunicipalCode("3550308");
        c.setCityCustomer("SAO PAULO");
        c.setNeighborhoodCustomer("JARDINS");
        c.setCountryCustomer("BRASIL");
        c.setZipCodeCustomer("01000-000");
        c.setPhoneCustomer("(11) 99999-9999");
        c.setCnpjCustomer("11.111.111/0001-11");
        c.setStateRegistry("123456");
        return c;
    }
}
