package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.CustomerModel;
import br.com.santospage.taxassistant.domain.repositories.CustomerRepository;
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
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldReturnCustomerWhenExists() {
        CustomerModel customer = mock(CustomerModel.class);
        when(customer.getCompanyCode()).thenReturn("01");
        when(customer.getCustomerId()).thenReturn("000001");
        when(customer.getNameCustomer()).thenReturn("CUSTOMER 001");
        when(customer.isActive()).thenReturn(true);

        when(repository.findByCompanyCodeAndCustomerId("01", "000001"))
                .thenReturn(Optional.of(customer));

        CustomerModel result = customerService.findByCompanyAndId("01", "000001");

        assertNotNull(result);
        assertEquals("01", result.getCompanyCode());
        assertEquals("000001", result.getCustomerId());
        assertEquals("CUSTOMER 001", result.getNameCustomer());

        verify(repository).findByCompanyCodeAndCustomerId("01", "000001");
    }

    @Test
    void shouldThrowWhenCustomerNotExists() {
        // Given
        when(repository.findByCompanyCodeAndCustomerId("01", "000099")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> customerService.findByCompanyAndId("01", "000099")
        );

        verify(repository).findByCompanyCodeAndCustomerId("01", "000099");
    }

    @Test
    void shouldReturnAllCustomers() {
        // Given
        CustomerModel customer1 = mock(CustomerModel.class);
        when(customer1.isActive()).thenReturn(true);

        CustomerModel customer2 = mock(CustomerModel.class);
        when(customer2.isActive()).thenReturn(true);

        when(repository.findAll(any(Sort.class))).thenReturn(List.of(customer1, customer2));

        // When
        List<CustomerModel> result = customerService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(CustomerModel::isActive));

        verify(repository).findAll(any(Sort.class));
    }

    @Test
    void shouldAllCustomersNotExists() {
        // Given
        when(repository.findAll(any(Sort.class))).thenReturn(List.of());

        // When
        List<CustomerModel> result = customerService.findAll();

        // Then
        assertTrue(result.isEmpty());

        verify(repository).findAll(any(Sort.class));
    }
}