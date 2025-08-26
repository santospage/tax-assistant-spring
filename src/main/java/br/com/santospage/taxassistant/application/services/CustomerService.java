package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.entities.Customer;
import br.com.santospage.taxassistant.domain.exceptions.CustomerNotFoundException;
import br.com.santospage.taxassistant.domain.repositories.CustomerRepository;
import br.com.santospage.taxassistant.interfaces.dtos.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    private CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    // Search customer by ID
    public CustomerDTO findById(String id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return toDTO(customer);
    }

    // Search all customers
    public List<CustomerDTO> findAll() {
        List<Customer> customers = repository.findAll();
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found");
        }
        return customers.stream()
                .map(this::toDTO)
                .toList();
    }

    private CustomerDTO toDTO(Customer entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.company = entity.getCompany();
        dto.id = entity.getId();
        dto.name = entity.getName();
        dto.natureCustomer = entity.getNatureCustomer();
        dto.address = entity.getAddress();
        dto.typeCustomer = entity.getTypeCustomer();
        dto.ufCustomer = entity.getUfCustomer();
        dto.municipalCode = entity.getMunicipalCode();
        dto.cityCustomer = entity.getCityCustomer();
        dto.neighborhoodCustomer = entity.getNeighborhoodCustomer();
        dto.countryCustomer = entity.getCountryCustomer();
        dto.zipCodeCustomer = entity.getZipCodeCustomer();
        dto.phoneCustomer = entity.getPhoneCustomer();
        dto.cnpjCustomer = entity.getCnpjCustomer();
        dto.stateRegistry = entity.getStateRegistry();
        return dto;
    }
}
