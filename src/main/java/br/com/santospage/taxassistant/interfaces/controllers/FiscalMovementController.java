package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.usecases.GetFiscalMovementsUseCase;
import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fiscal-movements")
public class FiscalMovementController {

    private final GetFiscalMovementsUseCase useCase;
    private final FiscalMovementRepository repository;

    public FiscalMovementController(GetFiscalMovementsUseCase useCase, FiscalMovementRepository repository) {
        this.useCase = useCase;
        this.repository = repository;
    }

    // Search for tableCode (ex: /api/fiscal-movements?table=SD2)
    @GetMapping(params = "table")
    public List<FiscalMovementDTO> getByTableCode(@RequestParam("table") String table) {
        return useCase.execute(table)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // Search /api/fiscal-movements/F2D123456
    @GetMapping("/{id}")
    public ResponseEntity<FiscalMovementDTO> getById(@PathVariable String id) {
        return useCase.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search all (ex: /api/fiscal-movements)
    @GetMapping
    public List<FiscalMovementDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private FiscalMovementDTO toDTO(FiscalMovement entity) {
        FiscalMovementDTO dto = new FiscalMovementDTO();
        dto.f2dFilial = entity.getCompanyCode();
        dto.fd2Idrel = entity.getMovementId();
        dto.f2dTabela = entity.getTable();
        return dto;
    }
}


