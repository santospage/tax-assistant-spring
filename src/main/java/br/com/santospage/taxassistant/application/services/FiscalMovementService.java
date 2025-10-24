package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.FiscalMovementModel;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class FiscalMovementService {

    private final FiscalMovementRepository repository;

    private FiscalMovementService(FiscalMovementRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<FiscalMovementModel> findAll() {
        return repository.findAll(Sort.by("companyCode", "movementId"))
                .stream()
                .filter(FiscalMovementModel::isActive)
                .toList();
    }

    public FiscalMovementModel findById(String id) {
        return repository.findById(id)
                .filter(FiscalMovementModel::isActive)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fiscal movement not found: " + id));
    }

    public List<FiscalMovementModel> findByTableMovement(String tableMovement) {
        List<FiscalMovementModel> list = repository.findByMovementTable(
                tableMovement
                , Sort.by("companyCode", "movementId")
        );

        List<FiscalMovementModel> filteredList = list.stream()
                .filter(FiscalMovementModel::isActive)
                .toList();

        if (filteredList.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Fiscal movement not found or inactive: " + tableMovement);
        }

        return filteredList;
    }
}
