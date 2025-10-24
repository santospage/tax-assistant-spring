package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.CustomerModel;
import br.com.santospage.taxassistant.domain.repositories.CustomerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerModel> findAll() {
        return repository.findAll(Sort.by("companyCode", "customerId"))
                .stream()
                .filter(CustomerModel::isActive)
                .toList();
    }

    public CustomerModel findByCompanyAndId(String company, String id) {
        return repository.findByCompanyCodeAndCustomerId(company, id)
                .filter(CustomerModel::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: "
                                                                 + id + company));
    }
}