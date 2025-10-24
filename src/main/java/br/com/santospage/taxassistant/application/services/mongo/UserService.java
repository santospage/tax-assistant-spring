package br.com.santospage.taxassistant.application.services.mongo;

import br.com.santospage.taxassistant.domain.enums.UserRole;
import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.exceptions.UserAlreadyExistsException;
import br.com.santospage.taxassistant.domain.models.mongo.UserModel;
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

    public UserModel create(String user, String fullName, String password,
                            String email, UserRole role) {

        if (repository.existsByUserName(user)) {
            throw new UserAlreadyExistsException(user);
        }

        UserModel newUser = new UserModel();
        newUser.setUserName(user);
        newUser.setUserFullName(fullName);
        newUser.setUserPassword(passwordEncoder.encode(password));
        newUser.setUserEmail(email);
        newUser.setUserRole(role);

        return repository.save(newUser);
    }

    public List<UserModel> getAllUsers() {
        return repository.findAll();
    }

    public UserModel getUserByUsername(String user) {
        return repository.findByUserName(user)
                .orElseThrow(() -> new ResourceNotFoundException(user));
    }

    public UserModel updateUser(String username, UserModel updatedUser) {
        UserModel existingUser = repository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException(username));

        existingUser.setUserFullName(updatedUser.getUserFullName());

        if (updatedUser.getUserPassword() != null && !updatedUser.getUserPassword().isEmpty()) {
            existingUser.setUserPassword(passwordEncoder.encode(updatedUser.getUserPassword()));
        }

        existingUser.setUserEmail(updatedUser.getUserEmail());
        existingUser.setUserRole(updatedUser.getUserRole());

        return repository.save(existingUser);
    }

    public void deleteUser(String username) {
        UserModel existingUser = repository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException(username));
        repository.delete(existingUser);
    }
}