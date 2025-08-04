package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiscalMovementsService {

    private final FiscalMovementRepository repository;

    private FiscalMovementsService(FiscalMovementRepository repository) {
        this.repository = repository;
    }

    public Optional<FiscalMovementDTO> findById(String id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public List<FiscalMovementDTO> findByTable(String table) {
        return repository.findByTable(table)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<FiscalMovementDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private FiscalMovementDTO toDTO(FiscalMovement entity) {
        FiscalMovementDTO dto = new FiscalMovementDTO();
        dto.company = entity.getCompanyCode();
        dto.idMovement = entity.getMovementId();
        dto.tableMovement = entity.getTable();
        dto.taxCode = entity.getTaxCode();
        dto.taxBase = entity.getTaxBase();
        dto.taxQuantity = entity.getTaxQuantity();
        dto.taxAliquot = entity.getTaxAliquot();
        dto.taxValue = entity.getTaxValue();
        dto.taxValueMargin = entity.getTaxValueMargin();
        dto.taxTariffValue = entity.getTaxTariffValue();
        dto.taxTributeAumented = entity.getTaxTributeAumented();
        dto.taxAliquotAumented = entity.getTaxAliquotAumented();
        dto.taxValueAumented = entity.getTaxValueAumented();
        return dto;
    }
}
