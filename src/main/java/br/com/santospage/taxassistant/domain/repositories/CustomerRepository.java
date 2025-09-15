package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.Customer;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    // Search all customers
    @Override
    @NotNull
    List<Customer> findAll();

    // Search customer by ID
    Optional<Customer> findByFilialAndId(String filial, String id);
}
