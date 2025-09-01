package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.domain.exceptions.FiscalMovementNotFoundException;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiscalMovementsService {

    private final FiscalMovementRepository repository;

    private FiscalMovementsService(FiscalMovementRepository repository) {
        this.repository = repository;
    }

    public FiscalMovementDTO findById(String id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new FiscalMovementNotFoundException("FiscalMovement not found with id: " + id));
    }

    public List<FiscalMovementDTO> findByTable(String table) {
        List<FiscalMovement> entities = repository.findByTable(table);

        if (entities.isEmpty()) {
            throw new FiscalMovementNotFoundException(
                    "No FiscalMovements found for table: " + table
            );
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
    
    public List<FiscalMovementDTO> findAll() {
        List<FiscalMovementDTO> results = repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();

        if (results.isEmpty()) {
            throw new FiscalMovementNotFoundException("No FiscalMovements found");
        }
        return results;
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
