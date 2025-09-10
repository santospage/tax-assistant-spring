package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.domain.models.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return ResponseEntity.ok(customers);
    }

    // Search customer by ID
    @GetMapping("{id}")
    public ResponseEntity<Customer> getById(@PathVariable String id) {
        Customer customer = service.findById(id);
        return ResponseEntity.ok(customer);
    }

}