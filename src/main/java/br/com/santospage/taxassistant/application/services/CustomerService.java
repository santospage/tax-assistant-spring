package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.CustomerNotFoundException;
import br.com.santospage.taxassistant.domain.models.Customer;
import br.com.santospage.taxassistant.domain.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findByFilialAndId(String filial, String id) {
        return repository.findByFilialAndId(filial, id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with ID: " + filial + id));
    }
}
