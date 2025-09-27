package br.com.santospage.taxassistant.interfaces.controllers.mongo;

import br.com.santospage.taxassistant.application.services.mongo.UserService;
import br.com.santospage.taxassistant.domain.enums.UserRole;
import br.com.santospage.taxassistant.domain.exceptions.UserAlreadyExistsException;
import br.com.santospage.taxassistant.domain.exceptions.UserNotFoundException;
import br.com.santospage.taxassistant.domain.models.mongo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUser_Success() throws Exception {
        User userRequest = new User();
        userRequest.setUser("testuser");
        userRequest.setFullName("Test User");
        userRequest.setPassword("123456");
        userRequest.setSalpass("sal123");
        userRequest.setEmail("test@example.com");
        userRequest.setRole(UserRole.ADMIN);

        User createdUser = new User();
        createdUser.setUser(userRequest.getUser());
        createdUser.setFullName(userRequest.getFullName());
        createdUser.setEmail(userRequest.getEmail());
        createdUser.setRole(userRequest.getRole());

        // Mock service to return the created user
        when(service.create(
                anyString(), anyString(), anyString(), anyString(), anyString(), any(UserRole.class)
        )).thenReturn(createdUser);

        mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("testuser"))
                .andExpect(jsonPath("$.fullName").value("Test User"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    void shouldCreateUser_UserAlreadyExists() throws Exception {
        User userRequest = new User();
        userRequest.setUser("testuser");
        userRequest.setFullName("Test User");
        userRequest.setPassword("123456");
        userRequest.setSalpass("sal123");
        userRequest.setEmail("test@example.com");
        userRequest.setRole(UserRole.ADMIN);

        // Mock service to throw UserAlreadyExistsException
        when(service.create(
                anyString(), anyString(), anyString(), anyString(), anyString(), any(UserRole.class)
        )).thenThrow(new UserAlreadyExistsException("User already exists"));

        mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isConflict()); // 409
    }

    @Test
    void updateUser_success() throws Exception {
        User updated = new User();
        updated.setUser("user01");
        updated.setFullName("User01");
        updated.setEmail("user01@example.com");

        Mockito.when(service.updateUser(eq("user01"), any(User.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/users/user01")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("User01"))
                .andExpect(jsonPath("$.email").value("user01@example.com"));
    }

    @Test
    void updateUser_notFound() throws Exception {
        User updated = new User();
        updated.setUser("user02");
        updated.setEmail("user02@example.com");

        Mockito.when(service.updateUser(eq("user02"), any(User.class)))
                .thenThrow(new UserNotFoundException("user02"));

        mockMvc.perform(put("/api/users/user02")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found: user02"));
    }

    @Test
    void deleteUser_success() throws Exception {
        Mockito.doNothing().when(service).deleteUser("user02");

        mockMvc.perform(delete("/api/users/user02"))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted: user02"));
    }

    @Test
    void deleteUser_notFound() throws Exception {
        Mockito.doThrow(new UserNotFoundException("user02"))
                .when(service).deleteUser("user02");

        mockMvc.perform(delete("/api/users/user02"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found: user02"));
    }

    @Test
    void getAllUsers_success() throws Exception {
        User u1 = new User();
        u1.setUser("user01");
        u1.setFullName("User 01");
        u1.setEmail("user01@example.com");

        User u2 = new User();
        u2.setUser("user02");
        u2.setFullName("User 02");
        u2.setEmail("user02@example.com");

        List<User> users = List.of(u1, u2);

        Mockito.when(service.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].user").value("user01"))
                .andExpect(jsonPath("$[1].user").value("user02"));
    }

    @Test
    void getUserByUsername_success() throws Exception {
        User u = new User();
        u.setUser("user01");
        u.setFullName("User 01");
        u.setEmail("user01@example.com");

        Mockito.when(service.getUserByUsername("user01")).thenReturn(u);

        mockMvc.perform(get("/api/users/user01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("user01"))
                .andExpect(jsonPath("$.fullName").value("User 01"))
                .andExpect(jsonPath("$.email").value("user01@example.com"));
    }

    @Test
    void getUserByUsername_notFound() throws Exception {
        Mockito.when(service.getUserByUsername("user99"))
                .thenThrow(new UserNotFoundException("user99"));

        mockMvc.perform(get("/api/users/user99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found: user99"));
    }

}
