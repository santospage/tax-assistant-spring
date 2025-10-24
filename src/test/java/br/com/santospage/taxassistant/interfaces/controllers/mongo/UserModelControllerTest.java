package br.com.santospage.taxassistant.interfaces.controllers.mongo;

import br.com.santospage.taxassistant.application.services.mongo.UserService;
import br.com.santospage.taxassistant.domain.enums.UserRole;
import br.com.santospage.taxassistant.domain.exceptions.ResourceNotFoundException;
import br.com.santospage.taxassistant.domain.exceptions.UserAlreadyExistsException;
import br.com.santospage.taxassistant.domain.models.mongo.UserModel;
import br.com.santospage.taxassistant.interfaces.dto.mongo.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUser_Success() throws Exception {
        // Arrange (request DTO enviado na requisição)
        UserDTO userRequest = new UserDTO();
        userRequest.setUserName("testuser");
        userRequest.setUserFullName("Test User");
        userRequest.setUserPassword("123456");
        userRequest.setUserEmail("test@example.com");
        userRequest.setUserRole(UserRole.ADMIN);

        UserModel createdUser = new UserModel();
        createdUser.setUserName(userRequest.getUserName());
        createdUser.setUserFullName(userRequest.getUserFullName());
        createdUser.setUserEmail(userRequest.getUserEmail());
        createdUser.setUserRole(userRequest.getUserRole());

        when(service.create(
                anyString(), anyString(), anyString(), anyString(), any(UserRole.class)
        )).thenReturn(createdUser);

        // Act + Assert
        mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("testuser"))
                .andExpect(jsonPath("$.userFullName").value("Test User"))
                .andExpect(jsonPath("$.userEmail").value("test@example.com"))
                .andExpect(jsonPath("$.userRole").value("ADMIN"));
    }

    @Test
    void shouldCreateUser_UserAlreadyExists() throws Exception {
        // Arrange
        UserDTO userRequest = new UserDTO();
        userRequest.setUserName("testuser");
        userRequest.setUserFullName("Test User");
        userRequest.setUserPassword("123456");
        userRequest.setUserEmail("test@example.com");
        userRequest.setUserRole(UserRole.ADMIN);

        // Mock
        when(service.create(
                anyString(), anyString(), anyString(), anyString(), any(UserRole.class)
        )).thenThrow(new UserAlreadyExistsException("testuser"));

        // Act + Assert
        mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isConflict()) // 409
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Conflict"))
                .andExpect(jsonPath("$.message").value("User already registered: testuser"))
                .andExpect(jsonPath("$.path").value("/api/users"));
    }

    @Test
    void updateUser_success() throws Exception {
        // Arrange
        UserDTO updated = new UserDTO();
        updated.setUserName("user01");
        updated.setUserFullName("User01");
        updated.setUserEmail("user01@example.com");

        UserModel updatedModel = new UserModel();
        updatedModel.setUserName("user01");
        updatedModel.setUserFullName("User01");
        updatedModel.setUserEmail("user01@example.com");

        Mockito.when(service.updateUser(eq("user01"), any(UserModel.class)))
                .thenReturn(updatedModel);

        // Act + Assert
        mockMvc.perform(put("/api/users/user01")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userFullName").value("User01"))
                .andExpect(jsonPath("$.userEmail").value("user01@example.com"));
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
        Mockito.doThrow(new ResourceNotFoundException("User not found: user02"))
                .when(service).deleteUser("user02");

        mockMvc.perform(delete("/api/users/user02"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsers_success() throws Exception {
        UserModel u1 = new UserModel();
        u1.setUserName("user01");
        u1.setUserFullName("User 01");
        u1.setUserEmail("user01@example.com");

        UserModel u2 = new UserModel();
        u2.setUserName("user02");
        u2.setUserFullName("User 02");
        u2.setUserEmail("user02@example.com");

        List<UserModel> users = List.of(u1, u2);

        // Mock do service
        Mockito.when(service.getAllUsers()).thenReturn(users);

        // Act + Assert
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].userName").value("user01"))
                .andExpect(jsonPath("$[0].userFullName").value("User 01"))
                .andExpect(jsonPath("$[0].userEmail").value("user01@example.com"))
                .andExpect(jsonPath("$[1].userName").value("user02"))
                .andExpect(jsonPath("$[1].userFullName").value("User 02"))
                .andExpect(jsonPath("$[1].userEmail").value("user02@example.com"));
    }

    @Test
    void getUserByUsername_success() throws Exception {
        // Arrange
        UserModel u = new UserModel();
        u.setUserName("user01");
        u.setUserFullName("User 01");
        u.setUserEmail("user01@example.com");

        Mockito.when(service.getUserByUsername("user01")).thenReturn(u);

        // Act + Assert
        mockMvc.perform(get("/api/users/user01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value("user01"))
                .andExpect(jsonPath("$.userFullName").value("User 01"))
                .andExpect(jsonPath("$.userEmail").value("user01@example.com"));
    }

    @Test
    void getUserByUsername_notFound() throws Exception {
        Mockito.when(service.getUserByUsername("user99"))
                .thenThrow(new ResourceNotFoundException("User not found: user99"));

        mockMvc.perform(get("/api/users/user99"))
                .andExpect(status().isNotFound());
    }
}
