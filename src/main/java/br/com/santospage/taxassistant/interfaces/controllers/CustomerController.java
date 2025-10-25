package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.CustomerService;
import br.com.santospage.taxassistant.domain.models.CustomerModel;
import br.com.santospage.taxassistant.interfaces.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<CustomerDTO>> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        Page<CustomerModel> customerPage = service.findAll(page, size);

        if (customerPage.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        List<CustomerDTO> customers = customerPage.stream()
                .map(CustomerDTO::new)
                .toList();

        return ResponseEntity.ok(customers); // 200
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