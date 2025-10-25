package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.CustomerModel;
import br.com.santospage.taxassistant.domain.repositories.CustomerRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Page<CustomerModel> findAll(Integer page, Integer size) {
        int currentPage = (page == null || page < 1) ? DEFAULT_PAGE : page - 1;
        int pageSize = (size == null || size < 1) ? DEFAULT_SIZE : size;

        Pageable pageable = PageRequest.of(
                currentPage, pageSize,
                Sort.by("companyCode", "customerId")
        );

        List<CustomerModel> activeCustomers = StreamSupport.stream(
                        repository.findAll(pageable).spliterator(), false)
                .map(customer -> customer.isActive() ? customer : null)
                .filter(Objects::nonNull)
                .filter(customer -> customer.getCustomerId() != null
                                    && !customer.getCustomerId().trim().isEmpty())
                .toList();

        return new PageImpl<>(activeCustomers, pageable, activeCustomers.size());
    }

    public CustomerModel findByCompanyAndId(String company, String id) {
        return repository.findByCompanyCodeAndCustomerId(company, id)
                .filter(CustomerModel::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: "
                                                                 + id + company));
    }
}