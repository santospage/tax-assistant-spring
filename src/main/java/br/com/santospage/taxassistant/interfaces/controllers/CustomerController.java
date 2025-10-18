package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.domain.models.CustomerModel;
import br.com.santospage.taxassistant.interfaces.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "customers", description = "Endpoints for managing customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // Search all customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        List<CustomerDTO> customers = service.findAll()
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());

        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build(); // Response 204
        }

        return ResponseEntity.ok(customers); // Response 200
    }

    // Search customer by ID
    @GetMapping(params = {"company", "id"})
    public ResponseEntity<CustomerDTO> getById(
            @RequestParam("company") String company,
            @RequestParam("id") String id) {

        CustomerModel customer = service.findByCompanyAndId(company, id);
        return ResponseEntity.ok(new CustomerDTO(customer));
    }
}