package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementsService;
import br.com.santospage.taxassistant.domain.models.FiscalMovement;
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

    private final FiscalMovementsService service;

    FiscalMovementController(FiscalMovementsService fiscalMovementService) {
        this.service = fiscalMovementService;
    }

    // Search /api/fiscal-movements/F2D123456
    @GetMapping("/{id}")
    public ResponseEntity<FiscalMovement> getById(@PathVariable String id) {
        FiscalMovement fm = service.findById(id);
        return ResponseEntity.ok(fm);
    }

    // Search for tableCode (ex: /api/fiscal-movements?table=SD2)
    @GetMapping("/table/{table}")
    public ResponseEntity<List<FiscalMovement>> getByTable(@PathVariable String table) {
        List<FiscalMovement> results = service.findByTableMovement(table);
        return ResponseEntity.ok(results);
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
}


