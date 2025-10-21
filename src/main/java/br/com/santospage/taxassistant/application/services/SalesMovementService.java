package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.SalesMovementModel;
import br.com.santospage.taxassistant.domain.repositories.SalesMovementRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class SalesMovementService {
    private final SalesMovementRepository repository;

    public SalesMovementService(SalesMovementRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SalesMovementModel> findAll() {
        return repository.findAll(Sort.by("companyCode", "documentNumber"))
                .stream()
                .filter(SalesMovementModel::isActive)
                .toList();
    }

    public SalesMovementModel findById(String id) {
        return repository.findById(id)
                .filter(SalesMovementModel::isActive)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sales movement not found: " + id));
    }

    public List<SalesMovementModel> findByCustomer(String customerCode) {
        List<SalesMovementModel> list = repository.findByCustomerCode(
                customerCode
                , Sort.by("companyCode", "documentNumber")
        );

        List<SalesMovementModel> filteredList = list.stream()
                .filter(SalesMovementModel::isActive)
                .toList();

        if (filteredList.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Sales movement not found or inactive: " + customerCode);
        }

        return filteredList;
    }

    public List<SalesMovementModel> findByProduct(String productCode) {
        List<SalesMovementModel> list = repository.findByProductCode(
                productCode
                , Sort.by("companyCode", "documentNumber")
        );

        List<SalesMovementModel> filteredList = list.stream()
                .filter(SalesMovementModel::isActive)
                .toList();

        if (filteredList.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Sales movement not found or inactive: " + productCode);
        }

        return filteredList;
    }
}
