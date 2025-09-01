package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.interfaces.dtos.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "customers", description = "Endpoints for managing customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    // Search /api/customers/000001
    @GetMapping("/{id}")
    @Operation(summary = "Search for a customer by ID",
            description = "Returns details of a specific customer by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerDTO> getById(@PathVariable String id) {
        CustomerDTO customer = service.findById(id);
        return ResponseEntity.ok(customer);
    }

    // Search all (ex: /api/customers)
    @GetMapping
    @Operation(summary = "Search all customers", description = "Returns details all customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers successfully found"),
            @ApiResponse(responseCode = "404", description = "Customers not found")
    })
    public ResponseEntity<List<CustomerDTO>> getAll(@RequestParam Map<String, String> allParams) {
        List<CustomerDTO> results = service.findAll();
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }
}
