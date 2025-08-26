package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.interfaces.dtos.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    // Search /api/customers/000001
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable String id) {
        CustomerDTO customer = service.findById(id);
        return ResponseEntity.ok(customer);
    }

    // Search all (ex: /api/customers)
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll(@RequestParam Map<String, String> allParams) {
        List<CustomerDTO> results = service.findAll();
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(results); // 200 OK
    }
}
