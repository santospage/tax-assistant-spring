package br.com.santospage.taxassistant.interfaces.controllers.mongo;

import br.com.santospage.taxassistant.application.services.mongo.UserService;
import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.models.mongo.UserModel;
import br.com.santospage.taxassistant.interfaces.dto.mongo.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    //GET all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = service.getAllUsers()
                .stream()
                .map(UserDTO::new)
                .toList();

        return ResponseEntity.ok(users);
    }

    //GET by user
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getByUser(@PathVariable("username") String username) {
        try {
            UserModel user = service.getUserByUsername(username);
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(userDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Create
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        UserModel created = service.create(
                userDTO.getUserName(),
                userDTO.getUserFullName(),
                userDTO.getUserPassword(),
                userDTO.getUserEmail(),
                userDTO.getUserRole()
        );

        return ResponseEntity.ok(new UserDTO(created));
    }

    //Update
    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("username") String username,
                                              @RequestBody UserDTO updatedUserDTO) {
        try {
            UserModel updatedUser = new UserModel();
            updatedUser.setUserName(updatedUserDTO.getUserName());
            updatedUser.setUserFullName(updatedUserDTO.getUserFullName());
            updatedUser.setUserPassword(updatedUserDTO.getUserPassword());
            updatedUser.setUserEmail(updatedUserDTO.getUserEmail());
            updatedUser.setUserRole(updatedUserDTO.getUserRole());

            UserModel user = service.updateUser(username, updatedUser);

            return ResponseEntity.ok(new UserDTO(user));

        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Delete
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        try {
            service.deleteUser(username);
            return ResponseEntity.ok("User deleted: " + username);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}

