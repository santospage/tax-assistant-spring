package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementService;
import br.com.santospage.taxassistant.domain.models.FiscalMovementModel;
import br.com.santospage.taxassistant.interfaces.dto.FiscalMovementDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fiscal-movements")
@Tag(name = "fiscal-movements", description = "Endpoints for managing fiscal-movements")
public class FiscalMovementController {

    private final FiscalMovementService service;

    FiscalMovementController(FiscalMovementService fiscalMovementService) {
        this.service = fiscalMovementService;
    }

    // Search all (ex: /api/fiscal-movements)
    @GetMapping
    public ResponseEntity<List<FiscalMovementDTO>> getAll() {
        List<FiscalMovementDTO> results = service.findAll()
                .stream()
                .map(FiscalMovementDTO::new)
                .toList();

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // Response 204
        }

        return ResponseEntity.ok(results); // Response 200
    }

    // Search /api/fiscal-movements/F2D123456
    @GetMapping("/{id}")
    public ResponseEntity<FiscalMovementDTO> getById(
            @Parameter(description = "Fiscal movement ID") @PathVariable String id) {
        FiscalMovementModel result = service.findById(id);
        return ResponseEntity.ok(new FiscalMovementDTO(result));
    }

    // Search for tableCode (ex: /api/fiscal-movements?table=SD2)
    @GetMapping("/table/{table}")
    public ResponseEntity<List<FiscalMovementDTO>> getByTable(
            @Parameter(description = "Fiscal movement table") @PathVariable String table) {
        List<FiscalMovementModel> results = service.findByTableMovement(table);
        List<FiscalMovementDTO> dtoList = results.stream()
                .map(FiscalMovementDTO::new)
                .toList();
        return ResponseEntity.ok(dtoList);
    }
}
