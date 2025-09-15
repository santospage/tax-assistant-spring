package br.com.santospage.taxassistant.domain.exceptions;

public class FiscalMovementNotFoundException extends RuntimeException {
    public FiscalMovementNotFoundException(String message) {
        super(message);
    }
}