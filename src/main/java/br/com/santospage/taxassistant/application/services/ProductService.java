package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ProductNotFoundException;
import br.com.santospage.taxassistant.domain.models.Product;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findByFilialAndId(String filial, String id) {
        return repository.findByFilialAndId(filial, id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with ID: " + filial + id));
    }
}
