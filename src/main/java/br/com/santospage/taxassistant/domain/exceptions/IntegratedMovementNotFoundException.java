package br.com.santospage.taxassistant.domain.exceptions;

public class IntegratedMovementNotFoundException extends RuntimeException {
    public IntegratedMovementNotFoundException(String message) {
        super(message);
    }
}