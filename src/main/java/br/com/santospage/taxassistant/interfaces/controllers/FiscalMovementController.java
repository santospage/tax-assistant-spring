package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementsService;
import br.com.santospage.taxassistant.domain.exceptions.FiscalMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.FiscalMovement;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/fiscal-movements")
@Tag(name = "fiscal-movements", description = "Endpoints for managing fiscal-movements")
public class FiscalMovementController {

    private final FiscalMovementsService service;

    FiscalMovementController(FiscalMovementsService fiscalMovementService) {
        this.service = fiscalMovementService;
    }

    // Search all (ex: /api/fiscal-movements)
    @GetMapping
    public ResponseEntity<List<FiscalMovement>> getAll() {
        List<FiscalMovement> results = service.findAll();

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // Response 204
        }

        return ResponseEntity.ok(results); // Response 200
    }

    // Search /api/fiscal-movements/F2D123456
    @GetMapping("/{id}")
    public ResponseEntity<FiscalMovement> getById(
            @Parameter(description = "Fiscal movement ID") @PathVariable String id) {
        try {
            FiscalMovement fm = service.findById(id);
            return ResponseEntity.ok(fm);
        } catch (FiscalMovementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    // Search for tableCode (ex: /api/fiscal-movements?table=SD2)
    @GetMapping("/table/{table}")
    public ResponseEntity<List<FiscalMovement>> getByTable(
            @Parameter(description = "Fiscal movement table") @PathVariable String table) {
        try {
            List<FiscalMovement> results = service.findByTableMovement(table);
            return ResponseEntity.ok(results);
        } catch (FiscalMovementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}


