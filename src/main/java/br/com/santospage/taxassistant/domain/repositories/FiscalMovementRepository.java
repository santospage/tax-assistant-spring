package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;

import java.time.LocalDate;
import java.util.List;

public interface FiscalMovementRepository {
    List<FiscalMovement> findByTable(String tableCode);
}
