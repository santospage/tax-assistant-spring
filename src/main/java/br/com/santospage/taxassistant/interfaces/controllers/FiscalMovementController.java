package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementService;
import br.com.santospage.taxassistant.domain.models.FiscalMovementModel;
import br.com.santospage.taxassistant.interfaces.dto.FiscalMovementDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<FiscalMovementDTO>> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        Page<FiscalMovementModel> fiscalPage = service.findAll(page, size);

        if (fiscalPage.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        List<FiscalMovementDTO> movements = fiscalPage.stream()
                .map(FiscalMovementDTO::new)
                .toList();

        return ResponseEntity.ok(movements); // 200
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
