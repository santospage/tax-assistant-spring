package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.SalesMovementModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalesMovementRepository
        extends JpaRepository<SalesMovementModel, String> {
    @NotNull Optional<SalesMovementModel> findById(@NotNull String id);

    List<SalesMovementModel> findByCustomerCode(String customerCode, Sort sort);

    List<SalesMovementModel> findByProductCode(String productCode, Sort sort);
}
