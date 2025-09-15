package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.FiscalMovement;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FiscalMovementRepository extends JpaRepository<FiscalMovement, String> {
    @NotNull Optional<FiscalMovement> findById(@NotNull String id);

    List<FiscalMovement> findByTableMovement(String tableMovement);

    @NotNull
    List<FiscalMovement> findAll();
}