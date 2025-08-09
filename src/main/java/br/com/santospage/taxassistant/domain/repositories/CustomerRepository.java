package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(String id);

    List<Customer> findAll();
}
