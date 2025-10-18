package br.com.santospage.taxassistant.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityMessage) {
        super(entityMessage);
    }
}