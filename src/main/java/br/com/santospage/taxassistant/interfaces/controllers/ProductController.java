package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.interfaces.dtos.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@Tag(name = "products", description = "Endpoints for managing products")
public class ProductController {
    private final ProductService service;

    ProductController(ProductService productService) {
        this.service = productService;
    }

    // Search /api/products/LJTEST01
    @GetMapping("/{id}")
    @Operation(summary = "Search for a product by ID",
            description = "Returns details of a specific product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product successfully found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductDTO> getById(@PathVariable String id) {
        ProductDTO product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    // Search all (ex: /api/products)
    @GetMapping
    @Operation(summary = "Search all products", description = "Returns details all product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products successfully found"),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    public ResponseEntity<List<ProductDTO>> getAll(@RequestParam Map<String, String> allParams) {
        List<ProductDTO> results = service.findAll();
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }
}
