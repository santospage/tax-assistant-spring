package br.com.santospage.taxassistant.application.services.mongo;

import br.com.santospage.taxassistant.domain.enums.UserRole;
import br.com.santospage.taxassistant.domain.exceptions.UserAlreadyExistsException;
import br.com.santospage.taxassistant.domain.exceptions.UserNotFoundException;
import br.com.santospage.taxassistant.domain.models.mongo.User;
import br.com.santospage.taxassistant.domain.repositories.mongo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_success() {
        User user = new User();
        user.setUserName("user01");
        
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");

        when(repository.existsByUsername("user01")).thenReturn(false);
        when(repository.save(any(User.class))).thenReturn(user);

        User result = service.create(
                "user01", "User 01", "123", "salt", UserRole.ADMIN
        );

        assertEquals("user01", result.getUserName());
        verify(repository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode("123");
    }

    @Test
    void createUser_alreadyExists() {
        when(repository.existsByUsername("user01")).thenReturn(true);

        assertThrows(
                UserAlreadyExistsException.class,
                () -> service.create("user01", "User 01", "123", "salt", UserRole.ADMIN)
        );

        verify(repository, never()).save(any(User.class));
    }

    @Test
    void getAllUsers_success() {
        User u1 = new User();
        u1.setUserName("user01");
        User u2 = new User();
        u2.setUserName("user02");

        when(repository.findAll()).thenReturn(List.of(u1, u2));

        List<User> result = service.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("user01", result.get(0).getUserName());
    }

    @Test
    void getUserByUsername_success() {
        User u = new User();
        u.setUserName("user01");

        when(repository.findByUsername("user01")).thenReturn(Optional.of(u));

        User result = service.getUserByUsername("user01");

        assertEquals("user01", result.getUserName());
    }

    @Test
    void getUserByUsername_notFound() {
        when(repository.findByUsername("user01")).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> service.getUserByUsername("user01")
        );
    }

    @Test
    void updateUser_success() {
        User existing = new User();
        existing.setUserName("user01");

        User updated = new User();
        updated.setFullName("Novo Nome");
        updated.setEmail("novo@example.com");

        when(repository.findByUsername("user01")).thenReturn(Optional.of(existing));
        when(repository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = service.updateUser("user01", updated);

        assertEquals("Novo Nome", result.getFullName());
        assertEquals("novo@example.com", result.getEmail());
        verify(repository).save(existing);
    }

    @Test
    void updateUser_notFound() {
        when(repository.findByUsername("user01")).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> service.updateUser("user01", new User())
        );
    }

    @Test
    void deleteUser_success() {
        User existing = new User();
        existing.setUserName("user01");

        when(repository.findByUsername("user01")).thenReturn(Optional.of(existing));

        service.deleteUser("user01");

        verify(repository, times(1)).delete(existing);
    }

    @Test
    void deleteUser_notFound() {
        when(repository.findByUsername("user01")).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> service.deleteUser("user01")
        );

        verify(repository, never()).delete(any(User.class));
    }
}

