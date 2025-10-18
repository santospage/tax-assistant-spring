package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.ProductService;
import br.com.santospage.taxassistant.domain.models.ProductModel;
import br.com.santospage.taxassistant.interfaces.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> products = service.findAll()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // Response 204
        }

        return ResponseEntity.ok(products); // Response 200
    }

    // Search product by ID
    @GetMapping(params = {"company", "id"})
    public ResponseEntity<ProductDTO> getById(
            @Parameter(description = "company") @RequestParam String company,
            @Parameter(description = "id") @RequestParam String id) {

        ProductModel product = service.findByCompanyAndId(company, id);
        return ResponseEntity.ok(new ProductDTO(product));
    }
}