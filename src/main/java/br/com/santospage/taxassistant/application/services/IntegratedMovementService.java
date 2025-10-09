package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.IntegratedMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.IntegratedMovement;
import br.com.santospage.taxassistant.domain.repositories.IntegratedMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegratedMovementService {

    private final IntegratedMovementRepository repository;

    public IntegratedMovementService(IntegratedMovementRepository repository) {
        this.repository = repository;
    }

    public List<IntegratedMovement> getAll() {
        List<IntegratedMovement> list = repository.findIntegratedMovements(null);
        if (list.isEmpty()) {
            throw new IntegratedMovementNotFoundException("No records found.");
        }
        return list;
    }

    public List<IntegratedMovement> getByCompany(String company) {
        List<IntegratedMovement> list = repository.findIntegratedMovements(company);
        if (list.isEmpty()) {
            throw new IntegratedMovementNotFoundException("No records found for the company: "
                                                          + company);
        }
        return list;
    }
}
