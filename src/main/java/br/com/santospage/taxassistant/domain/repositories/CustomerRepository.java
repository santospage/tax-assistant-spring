package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerModel, String> {

    // Search customer by ID
    Optional<CustomerModel> findByCompanyCodeAndCustomerId(String companyCode
            , String customerId);
}
