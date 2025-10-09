package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.SalesMovementService;
import br.com.santospage.taxassistant.domain.exceptions.FiscalMovementNotFoundException;
import br.com.santospage.taxassistant.domain.exceptions.SalesMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.SalesMovement;
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
@RequestMapping("/api/sales-movements")
@Tag(name = "sales-movements", description = "Endpoints for managing sales-movements")
public class SalesMovementController {
    private final SalesMovementService salesMovementService;

    SalesMovementController(SalesMovementService salesMovementService) {
        this.salesMovementService = salesMovementService;
    }

    // Search all (ex: /api/sales-movements)
    @GetMapping
    public ResponseEntity<List<SalesMovement>> getAll() {
        List<SalesMovement> results = salesMovementService.findAll();

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // Response 204
        }

        return ResponseEntity.ok(results); // Response 200
    }

    // Search /api/sales-movements/F2D123456
    @GetMapping("/{id}")
    public ResponseEntity<SalesMovement> getById(
            @Parameter(description = "Sales movement ID") @PathVariable String id) {
        try {
            SalesMovement sm = salesMovementService.findById(id);
            return ResponseEntity.ok(sm);
        } catch (SalesMovementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // Search for customerCode (ex: /api/sales-movements/customer/customer001)
    @GetMapping("/customer/{customerCode}")
    public ResponseEntity<List<SalesMovement>> getByCustomer(
            @Parameter(description = "Sales movement customer") @PathVariable String customerCode) {
        try {
            List<SalesMovement> results = salesMovementService.findByCustomer(customerCode);
            return ResponseEntity.ok(results);
        } catch (FiscalMovementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // Search for productCode (ex: /api/sales-movements/product/product001)
    @GetMapping("/product/{productCode}")
    public ResponseEntity<List<SalesMovement>> getByProduct(
            @Parameter(description = "Sales movement product") @PathVariable String productCode) {
        try {
            List<SalesMovement> results = salesMovementService.findByProduct(productCode);
            return ResponseEntity.ok(results);
        } catch (FiscalMovementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
