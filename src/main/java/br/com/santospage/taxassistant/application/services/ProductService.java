package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.ProductModel;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductModel> findAll() {
        return repository.findAll()
                .stream()
                .filter(ProductModel::isActive)
                .toList();
    }

    public ProductModel findByCompanyAndId(String company, String id) {
        return repository.findByCompanyAndId(company, id)
                .filter(ProductModel::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: "
                                                                 + id + company));
    }
}
