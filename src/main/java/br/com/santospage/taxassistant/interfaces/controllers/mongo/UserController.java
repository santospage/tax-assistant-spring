package br.com.santospage.taxassistant.interfaces.controllers.mongo;

import br.com.santospage.taxassistant.application.services.mongo.UserService;
import br.com.santospage.taxassistant.domain.exceptions.UserAlreadyExistsException;
import br.com.santospage.taxassistant.domain.exceptions.UserNotFoundException;
import br.com.santospage.taxassistant.domain.models.mongo.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userRequest) {
        User created = service.create(
                userRequest.getUser(),
                userRequest.getFullName(),
                userRequest.getPassword(),
                userRequest.getSalpass(),
                userRequest.getEmail(),
                userRequest.getRole()
        );
        return ResponseEntity.ok(created);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(UserAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //GET all users
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //GET user by user
    @GetMapping("/{username}")
    public ResponseEntity<?> getByUser(@PathVariable("username") String username) {
        try {
            User user = service.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    //Update
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable("username") String username,
                                        @RequestBody User updatedUser) {
        try {
            User user = service.updateUser(username, updatedUser);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    //Delete
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        try {
            service.deleteUser(username);
            return ResponseEntity.ok("User deleted: " + username);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}

