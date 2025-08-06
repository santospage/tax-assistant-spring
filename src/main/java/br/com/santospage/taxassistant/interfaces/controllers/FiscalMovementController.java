package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementsService;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fiscal-movements")
public class FiscalMovementController {

    private final FiscalMovementsService service;

    private FiscalMovementController(FiscalMovementsService fiscalMovementService) {
        this.service = fiscalMovementService;
    }

    // Search /api/fiscal-movements/F2D123456
    @GetMapping("/{id}")
    public ResponseEntity<FiscalMovementDTO> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search for tableCode (ex: /api/fiscal-movements?table=SD2) ***
    @GetMapping(params = "table")
    public ResponseEntity<List<FiscalMovementDTO>> getByTableCode(@RequestParam("table") String table) {
        List<FiscalMovementDTO> results = service.findByTable(table);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }

    // Search all (ex: /api/fiscal-movements)
    @GetMapping
    public ResponseEntity<List<FiscalMovementDTO>> getAll(@RequestParam Map<String, String> allParams) {
        List<FiscalMovementDTO> results = service.findAll();
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }
}


