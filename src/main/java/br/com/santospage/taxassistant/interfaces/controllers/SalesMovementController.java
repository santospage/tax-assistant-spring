package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.SalesMovementService;
import br.com.santospage.taxassistant.domain.models.SalesMovementModel;
import br.com.santospage.taxassistant.interfaces.dto.SalesMovementDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<SalesMovementDTO>> getAll() {
        List<SalesMovementDTO> results = salesMovementService.findAll()
                .stream()
                .map(SalesMovementDTO::new)
                .toList();

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // Response 204
        }

        return ResponseEntity.ok(results); // Response 200
    }

    // Search /api/sales-movements/F2D123456
    @GetMapping("/{id}")
    public ResponseEntity<SalesMovementDTO> getById(
            @Parameter(description = "Sales movement ID") @PathVariable String id) {
        SalesMovementModel result = salesMovementService.findById(id);
        return ResponseEntity.ok(new SalesMovementDTO(result));
    }

    // Search for customerCode (ex: /api/sales-movements/customer/customer001)
    @GetMapping("/customer/{customerCode}")
    public ResponseEntity<List<SalesMovementDTO>> getByCustomer(
            @Parameter(description = "Sales movement customer") @PathVariable String customerCode) {
        List<SalesMovementModel> results = salesMovementService.findByCustomer(customerCode);
        List<SalesMovementDTO> dtoList = results.stream()
                .map(SalesMovementDTO::new)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    // Search for productCode (ex: /api/sales-movements/product/product001)
    @GetMapping("/product/{productCode}")
    public ResponseEntity<List<SalesMovementDTO>> getByProduct(
            @Parameter(description = "Sales movement product") @PathVariable String productCode) {
        List<SalesMovementModel> results = salesMovementService.findByProduct(productCode);
        List<SalesMovementDTO> dtoList = results.stream()
                .map(SalesMovementDTO::new)
                .toList();
        return ResponseEntity.ok(dtoList);
    }
}
