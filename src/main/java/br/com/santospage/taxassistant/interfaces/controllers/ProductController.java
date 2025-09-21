package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.domain.exceptions.ProductNotFoundException;
import br.com.santospage.taxassistant.domain.models.Product;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "products", description = "Endpoints for managing products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Search all products
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = service.findAll();

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // Response 204
        }

        return ResponseEntity.ok(products); // Response 200
    }

    // Search product by ID
    @GetMapping(params = {"company", "id"})
    public ResponseEntity<Product> getById(@RequestParam String company, @RequestParam String id) {
        try {
            Product product = service.findByFilialAndId(company, id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}