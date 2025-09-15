package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.domain.models.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // Search all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = service.findAll();

        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build(); // Response 204
        }

        return ResponseEntity.ok(customers); // Response 200
    }

    // Search customer by ID
    @GetMapping(params = {"company", "id"})
    public Customer getById(@RequestParam String company, @RequestParam String id) {
        return service.findByFilialAndId(company, id);
    }
}