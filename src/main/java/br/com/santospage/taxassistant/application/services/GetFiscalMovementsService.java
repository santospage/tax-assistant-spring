package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.application.usecases.GetFiscalMovementsUseCase;
import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFiscalMovementsService implements GetFiscalMovementsUseCase {

    private final FiscalMovementRepository repository;

    public GetFiscalMovementsService(FiscalMovementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FiscalMovement> execute(String tableCode) {
        return repository.findByTable(tableCode);
    }
}
