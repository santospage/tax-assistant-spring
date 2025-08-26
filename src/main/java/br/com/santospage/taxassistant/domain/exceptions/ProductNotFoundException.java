package br.com.santospage.taxassistant.domain.exceptions;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}