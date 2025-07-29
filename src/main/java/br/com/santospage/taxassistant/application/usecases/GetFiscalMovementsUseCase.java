package br.com.santospage.taxassistant.application.usecases;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;

import java.util.List;
import java.util.Optional;

public interface GetFiscalMovementsUseCase {
    List<FiscalMovement> execute(String table);

    Optional<FiscalMovement> findById(String id);
}
