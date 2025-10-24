package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.IntegratedMovementModel;
import br.com.santospage.taxassistant.domain.repositories.IntegratedMovementRepository;
import br.com.santospage.taxassistant.interfaces.dto.IntegratedMovementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegratedMovementService {

    private final IntegratedMovementRepository repository;

    public IntegratedMovementService(IntegratedMovementRepository repository) {
        this.repository = repository;
    }

    public List<IntegratedMovementDTO> getAll() {
        List<IntegratedMovementModel> list = repository.findIntegratedMovements(null);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No records found.");
        }

        return list.stream()
                .map(m -> new IntegratedMovementDTO(
                        m.companyCode(),
                        m.taxId(),
                        m.descriptionTax()
                ))
                .toList();
    }

    public List<IntegratedMovementDTO> getByCompany(String company) {
        List<IntegratedMovementModel> list = repository.findIntegratedMovements(company);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No records found for the company: " + company
            );
        }

        return list.stream()
                .map(m -> new IntegratedMovementDTO(
                        m.companyCode(),
                        m.taxId(),
                        m.descriptionTax()
                ))
                .toList();
    }
}
