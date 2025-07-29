package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;

import java.util.List;
import java.util.Optional;

public interface FiscalMovementRepository {
    Optional<FiscalMovement> findById(String id);

    List<FiscalMovement> findAll();

    FiscalMovement save(FiscalMovement movement);

    List<FiscalMovement> findByTable(String table);
}