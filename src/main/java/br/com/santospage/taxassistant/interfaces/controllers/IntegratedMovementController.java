package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.IntegratedMovementService;
import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.interfaces.dto.IntegratedMovementDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/integrated-movements")
public class IntegratedMovementController {

    private final IntegratedMovementService service;

    public IntegratedMovementController(IntegratedMovementService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<IntegratedMovementDTO> list = service.getAll();
            return ResponseEntity.ok(list);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", HttpStatus.NOT_FOUND.value(),
                            "error", "Not Found",
                            "message", ex.getMessage()
                    ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "error", "Internal Server Error",
                            "message", ex.getMessage()
                    ));
        }
    }

    @GetMapping("/{company}")
    public ResponseEntity<Object> getByCompany(@PathVariable String company) {
        try {
            List<IntegratedMovementDTO> list = service.getByCompany(company);
            return ResponseEntity.ok(list);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", HttpStatus.NOT_FOUND.value(),
                            "error", "Not Found",
                            "message", ex.getMessage()
                    ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "error", "Internal Server Error",
                            "message", ex.getMessage()
                    ));
        }
    }
}