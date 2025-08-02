package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetFiscalMovementsService {

    private final FiscalMovementRepository repository;

    private GetFiscalMovementsService(FiscalMovementRepository repository) {
        this.repository = repository;
    }

    //@Override
    private List<FiscalMovement> execute(String table) {
        return repository.findByTable(table);
    }

    //@Override
    private Optional<FiscalMovement> findById(String id) {
        return repository.findById(id);
    }
}
