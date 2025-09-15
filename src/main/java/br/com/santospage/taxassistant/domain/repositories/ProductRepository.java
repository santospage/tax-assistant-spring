package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    // Search all customers
    @Override
    @NotNull
    List<Product> findAll();

    // Search customer by ID
    Optional<Product> findByFilialAndId(String filial, String id);
}
