package br.com.santospage.taxassistant.interfaces.controllers.mongo;

import br.com.santospage.taxassistant.application.services.mongo.UserService;
import br.com.santospage.taxassistant.domain.enums.UserRole;
import br.com.santospage.taxassistant.domain.exceptions.UserAlreadyExistsException;
import br.com.santospage.taxassistant.domain.models.mongo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
