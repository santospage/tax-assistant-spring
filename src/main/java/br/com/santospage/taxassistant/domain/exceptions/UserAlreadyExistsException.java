package br.com.santospage.taxassistant.domain.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("User already registered: " + username);
    }
}
