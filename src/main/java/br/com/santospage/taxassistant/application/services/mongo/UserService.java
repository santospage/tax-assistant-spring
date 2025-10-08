package br.com.santospage.taxassistant.application.services.mongo;

import br.com.santospage.taxassistant.domain.enums.UserRole;
import br.com.santospage.taxassistant.domain.exceptions.UserAlreadyExistsException;
import br.com.santospage.taxassistant.domain.exceptions.UserNotFoundException;
import br.com.santospage.taxassistant.domain.models.mongo.User;
import br.com.santospage.taxassistant.domain.repositories.mongo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String user, String fullName, String password,
                       String email, UserRole role) {

        if (repository.existsByUserName(user)) {
            throw new UserAlreadyExistsException(user);
        }

        User newUser = new User();
        newUser.setUserName(user);
        newUser.setFullName(fullName);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setRole(role);

        return repository.save(newUser);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserByUsername(String user) {
        return repository.findByUserName(user)
                .orElseThrow(() -> new UserNotFoundException(user));
    }

    public User updateUser(String username, User updatedUser) {
        User existingUser = repository.findByUserName(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        existingUser.setFullName(updatedUser.getFullName());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());

        return repository.save(existingUser);
    }

    public void deleteUser(String username) {
        User existingUser = repository.findByUserName(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        repository.delete(existingUser);
    }
}