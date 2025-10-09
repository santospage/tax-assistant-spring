package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.SalesMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.SalesMovement;
import br.com.santospage.taxassistant.domain.repositories.SalesMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesMovementService {
    private final SalesMovementRepository repository;

    public SalesMovementService(SalesMovementRepository repository) {
        this.repository = repository;
    }

    public SalesMovement findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new SalesMovementNotFoundException(
                        "Sales movement not found: " + id));
    }

    public List<SalesMovement> findByCustomer(String customerCode) {
        List<SalesMovement> list = repository.findByCustomerCode(customerCode);
        if (list.isEmpty()) {
            throw new SalesMovementNotFoundException(
                    "Sales movement not found: " + customerCode);
        }
        return list;
    }

    public List<SalesMovement> findByProduct(String productCode) {
        List<SalesMovement> list = repository.findByProductCode(productCode);
        if (list.isEmpty()) {
            throw new SalesMovementNotFoundException(
                    "Sales movement not found: " + productCode);
        }
        return list;
    }

    public List<SalesMovement> findAll() {
        return repository.findAll();
    }
}
