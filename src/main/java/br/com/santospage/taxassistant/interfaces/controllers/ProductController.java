package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.interfaces.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    private ProductController(ProductService productService) {
        this.service = productService;
    }

    // Search /api/products/LJTEST01
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search all (ex: /api/products)
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(@RequestParam Map<String, String> allParams) {
        List<ProductDTO> results = service.findAll();
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }

}
