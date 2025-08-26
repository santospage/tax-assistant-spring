package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.FiscalMovementsService;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fiscal-movements")
public class FiscalMovementController {

    private final FiscalMovementsService service;

    FiscalMovementController(FiscalMovementsService fiscalMovementService) {
        this.service = fiscalMovementService;
    }

    // Search /api/fiscal-movements/F2D123456
    @GetMapping("/{id}")
    public ResponseEntity<FiscalMovementDTO> getById(@PathVariable String id) {
        FiscalMovementDTO fm = service.findById(id);
        return ResponseEntity.ok(fm);
    }

    // Search for tableCode (ex: /api/fiscal-movements?table=SD2) ***
    @GetMapping("/table/{table}")
    public ResponseEntity<List<FiscalMovementDTO>> getByTable(@PathVariable String table) {
        List<FiscalMovementDTO> results = service.findByTable(table);
        return ResponseEntity.ok(results);
    }

    // Search all (ex: /api/fiscal-movements)
    @GetMapping
    public ResponseEntity<List<FiscalMovementDTO>> getAll() {
        List<FiscalMovementDTO> results = service.findAll();
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(results); // 200 OK
    }
}


