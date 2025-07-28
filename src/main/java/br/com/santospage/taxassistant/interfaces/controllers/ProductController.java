package br.com.santospage.taxassistant.interfaces.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProductController<ProductDTO> {
    //private final ProdutoService service;

    //@GetMapping("/{id}")
    //public ProductDTO buscar(@PathVariable String id) {
        //return service.find(id);
    //}
}
