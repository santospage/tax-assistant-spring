package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.ProductModel;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductModel> findAll() {
        return repository.findAll(Sort.by("companyCode", "productId"))
                .stream()
                .filter(ProductModel::isActive)
                .toList();
    }

    public ProductModel findByCompanyAndId(String companyCode, String productId) {
        return repository.findByCompanyCodeAndProductId(companyCode, productId)
                .filter(ProductModel::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: "
                                                                 + companyCode + productId));
    }
}
