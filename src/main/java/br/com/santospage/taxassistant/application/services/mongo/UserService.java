package br.com.santospage.taxassistant.application.services.mongo;

import br.com.santospage.taxassistant.domain.enums.UserRole;
import br.com.santospage.taxassistant.domain.exceptions.UserAlreadyExistsException;
import br.com.santospage.taxassistant.domain.exceptions.UserNotFoundException;
import br.com.santospage.taxassistant.domain.models.mongo.User;
import br.com.santospage.taxassistant.domain.repositories.mongo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(String user, String fullName, String password,
                       String salpass, String email, UserRole role) {

        if (repository.existsByUser(user)) {
            throw new UserAlreadyExistsException(user);
        }
        
        User newUser = new User();
        newUser.setUser(user);
        newUser.setFullName(fullName);
        newUser.setPassword(password); // after to do BCrypt
        newUser.setSalpass(salpass);
        newUser.setEmail(email);
        newUser.setRole(role);

        return repository.save(newUser);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserByUsername(String user) {
        return repository.findByUser(user)
                .orElseThrow(() -> new UserNotFoundException(user));
    }

    public User updateUser(String username, User updatedUser) {
        User existingUser = repository.findByUser(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setSalpass(updatedUser.getSalpass());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());

        return repository.save(existingUser);
    }

    public void deleteUser(String username) {
        User existingUser = repository.findByUser(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        repository.delete(existingUser);
    }
}