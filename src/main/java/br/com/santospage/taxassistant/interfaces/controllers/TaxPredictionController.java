package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.TaxPredictionService;
import br.com.santospage.taxassistant.interfaces.dto.TaxPredictionDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax-predictions")
@Tag(name = "tax-predictions", description = "Endpoints for managing taxes predictions")
public class TaxPredictionController {

    private final TaxPredictionService service;

    public TaxPredictionController(TaxPredictionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<TaxPredictionDTO>> predict(
            @RequestBody TaxPredictionDTO request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                service.predict(request, page, size)
        );
    }
}



