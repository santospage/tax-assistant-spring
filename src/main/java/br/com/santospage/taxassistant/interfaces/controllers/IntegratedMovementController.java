package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.IntegratedMovementService;
import br.com.santospage.taxassistant.domain.exceptions.IntegratedMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.IntegratedMovement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    public ResponseEntity<?> getAll() {
        try {
            List<IntegratedMovement> list = service.getAll();
            return ResponseEntity.ok(list);
        } catch (IntegratedMovementNotFoundException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.NOT_FOUND.value());
            error.put("error", "Not Found");
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.put("error", "Internal Server Error");
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{company}")
    public ResponseEntity<?> getByCompany(@PathVariable String company) {
        try {
            List<IntegratedMovement> list = service.getByCompany(company);
            return ResponseEntity.ok(list);
        } catch (IntegratedMovementNotFoundException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.NOT_FOUND.value());
            error.put("error", "Not Found");
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.put("error", "Internal Server Error");
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}