package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.FiscalMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.FiscalMovement;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiscalMovementsService {

    private final FiscalMovementRepository repository;

    private FiscalMovementsService(FiscalMovementRepository repository) {
        this.repository = repository;
    }

    public FiscalMovement findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new FiscalMovementNotFoundException(
                        "Fiscal movement not found: " + id));
    }

    public List<FiscalMovement> findByTableMovement(String tableMovement) {
        List<FiscalMovement> list = repository.findByTableMovement(tableMovement);
        if (list.isEmpty()) {
            throw new FiscalMovementNotFoundException(
                    "Fiscal movement not found: " + tableMovement);
        }
        return list;
    }

    public List<FiscalMovement> findAll() {
        return repository.findAll();
    }
}
