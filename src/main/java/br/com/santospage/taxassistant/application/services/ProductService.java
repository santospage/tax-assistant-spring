package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.ProductModel;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Page<ProductModel> findAll(Integer page, Integer size) {
        int currentPage = (page == null || page < 1) ? DEFAULT_PAGE : page - 1;
        int pageSize = (size == null || size < 1) ? DEFAULT_SIZE : size;

        Pageable pageable = PageRequest.of(
                currentPage, pageSize,
                Sort.by("companyCode", "productId")
        );

        List<ProductModel> activeProducts = StreamSupport.stream(
                        repository.findAll(pageable).spliterator(), false)
                .map(product -> product.isActive() ? product : null)
                .filter(Objects::nonNull)
                .filter(product -> product.getProductId() != null
                                   && !product.getProductId().trim().isEmpty())
                .toList();

        return new PageImpl<>(activeProducts, pageable, activeProducts.size());
    }

    public ProductModel findByCompanyAndId(String companyCode, String productId) {
        return repository.findByCompanyCodeAndProductId(companyCode, productId)
                .filter(ProductModel::isActive)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: "
                                                                 + companyCode + productId));
    }
}
