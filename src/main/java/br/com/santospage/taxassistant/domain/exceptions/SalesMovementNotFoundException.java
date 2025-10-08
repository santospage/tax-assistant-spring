package br.com.santospage.taxassistant.domain.exceptions;

public class SalesMovementNotFoundException extends RuntimeException {
    public SalesMovementNotFoundException(String message) {
        super(message);
    }
}
