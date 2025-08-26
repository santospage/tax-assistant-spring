package br.com.santospage.taxassistant.domain.exceptions;

public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}

