package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, String> {

    // Search customer by ID
    Optional<ProductModel> findByCompanyCodeAndProductId(String companyCode
            , String productId);
}
