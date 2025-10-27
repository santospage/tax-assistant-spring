package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.SalesMovementModel;
import br.com.santospage.taxassistant.domain.repositories.SalesMovementRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class SalesMovementService {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private final SalesMovementRepository repository;

    public SalesMovementService(SalesMovementRepository repository) {
        this.repository = repository;
    }

    public Page<SalesMovementModel> findAll(Integer page, Integer size) {
        int currentPage = (page == null || page < 1) ? DEFAULT_PAGE : page - 1;
        int pageSize = (size == null || size < 1) ? DEFAULT_SIZE : size;

        Pageable pageable = PageRequest.of(
                currentPage, pageSize,
                Sort.by("companyCode", "documentNumber")
        );

        List<SalesMovementModel> activeMovements = StreamSupport.stream(
                        repository.findAll(pageable).spliterator(), false)
                .map(movement -> movement.isActive() ? movement : null)
                .filter(Objects::nonNull)
                .filter(movement -> movement.getDocumentNumber() != null
                                    && !movement.getDocumentNumber().trim().isEmpty())
                .toList();

        return new PageImpl<>(activeMovements, pageable, activeMovements.size());
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
