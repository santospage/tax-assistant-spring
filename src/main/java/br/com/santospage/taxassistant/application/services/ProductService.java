package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ProductNotFoundException;
import br.com.santospage.taxassistant.domain.models.Product;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import br.com.santospage.taxassistant.interfaces.dtos.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    private ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Search product by ID
    public ProductDTO findById(String id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Customer not found with id: " + id));
        return toDTO(product);
    }

    // Search all products
    public List<ProductDTO> findAll() {
        List<Product> product = repository.findAll();
        if (product.isEmpty()) {
            throw new ProductNotFoundException("No product found");
        }
        return product.stream()
                .map(this::toDTO)
                .toList();
    }

    private ProductDTO toDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.company = entity.getCompany();
        dto.id = entity.getId();
        dto.name = entity.getName();
        dto.typeProduct = entity.getTypeProduct();
        dto.specifingCodeST = entity.getSpecifingCodeST();
        dto.unitMeasure = entity.getUnitMeasure();
        dto.unitValue = entity.getUnitValue();
        dto.standarOutflowCode = entity.getStandarOutflowCode();
        dto.standarInflowCode = entity.getStandarInflowCode();
        dto.mercosulExtNomenclature = entity.getMercosulExtNomenclature();
        return dto;
    }
}
