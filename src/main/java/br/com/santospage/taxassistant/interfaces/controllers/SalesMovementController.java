package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.SalesMovementService;
import br.com.santospage.taxassistant.domain.models.SalesMovementModel;
import br.com.santospage.taxassistant.interfaces.dto.SalesMovementDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<SalesMovementDTO>> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        Page<SalesMovementModel> salesPage = salesMovementService.findAll(page, size);

        if (salesPage.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        List<SalesMovementDTO> movements = salesPage.stream()
                .map(SalesMovementDTO::new)
                .toList();

        return ResponseEntity.ok(movements); // 200
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
