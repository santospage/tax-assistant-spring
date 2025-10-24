package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.ProductModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, String> {

    // Search all customers
    @Override
    @NotNull
    List<ProductModel> findAll();

    // Search customer by ID
    Optional<ProductModel> findByCompanyCodeAndProductId(String companyCode, String productId);
}
