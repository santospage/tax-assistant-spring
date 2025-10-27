package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.FiscalMovementModel;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class FiscalMovementService {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private final FiscalMovementRepository repository;

    private FiscalMovementService(FiscalMovementRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<FiscalMovementModel> findAll(Integer page, Integer size) {
        int currentPage = (page == null || page < 1) ? DEFAULT_PAGE : page - 1;
        int pageSize = (size == null || size < 1) ? DEFAULT_SIZE : size;

        Pageable pageable = PageRequest.of(
                currentPage, pageSize,
                Sort.by("companyCode", "movementId")
        );

        List<FiscalMovementModel> activeMovements = StreamSupport.stream(
                        repository.findAll(pageable).spliterator(), false)
                .map(movement -> movement.isActive() ? movement : null)
                .filter(Objects::nonNull)
                .filter(movement -> movement.getMovementId() != null
                                    && !movement.getMovementId().trim().isEmpty())
                .toList();

        return new PageImpl<>(activeMovements, pageable, activeMovements.size());
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
