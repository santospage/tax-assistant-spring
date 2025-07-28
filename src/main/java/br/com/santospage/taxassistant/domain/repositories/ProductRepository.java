package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String id);
    List<Product> findAll();
    Product save(Product product);
}
