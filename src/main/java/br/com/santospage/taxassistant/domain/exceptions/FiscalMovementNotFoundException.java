package br.com.santospage.taxassistant.domain.exceptions;

public class FiscalMovementNotFoundException extends EntityNotFoundException {
    public FiscalMovementNotFoundException(String message) {
        super(message);
    }
}