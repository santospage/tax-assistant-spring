package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.FiscalMovementModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FiscalMovementRepository extends JpaRepository<FiscalMovementModel, String> {
    @NotNull Optional<FiscalMovementModel> findById(@NotNull String id);

    List<FiscalMovementModel> findByMovementTable(String movementTable, Sort sort);
}