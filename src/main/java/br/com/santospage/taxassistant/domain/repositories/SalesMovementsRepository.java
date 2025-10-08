package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.SalesMovement;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalesMovementsRepository extends JpaRepository<SalesMovement, String> {
    @NotNull Optional<SalesMovement> findById(@NotNull String id);

    List<SalesMovement> findByCustomerCode(String customerCode);

    List<SalesMovement> findByProductCode(String productCode);

    @NotNull
    List<SalesMovement> findAll();
}
