package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.FiscalMovement;

import java.util.List;
import java.util.Optional;

public interface FiscalMovementRepository {
    Optional<FiscalMovement> findById(String id);

    List<FiscalMovement> findByTable(String table);

    List<FiscalMovement> findAll();
}