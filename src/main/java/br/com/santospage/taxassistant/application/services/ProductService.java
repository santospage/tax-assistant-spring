package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.entities.Product;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import br.com.santospage.taxassistant.interfaces.dtos.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    private ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<ProductDTO> findById(String id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public List<ProductDTO> findAll() {
        return repository.findAll()
                .stream()
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
