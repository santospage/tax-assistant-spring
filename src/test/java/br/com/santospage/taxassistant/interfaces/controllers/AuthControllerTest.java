package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.infrastructure.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AuthController.class,
        excludeAutoConfiguration = org.springframework.boot.autoconfigure
                .security.servlet.SecurityAutoConfiguration.class)
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authManager;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @Test
    void login_success_returnsToken() throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                new User("user01", "123", java.util.Collections.emptyList()),
                null
        );

        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(auth);
        when(tokenProvider.createToken(any(), any()))
                .thenReturn("fake-jwt-token");

        String requestBody = """
                {
                    "user": "user01",
                    "password": "123"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"fake-jwt-token\"}"));
    }

    @Test
    void login_fail_returns401() throws Exception {
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        String requestBody = """
                {
                    "user": "user01",
                    "password": "wrongpass"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json("{\"error\":\"Invalid credentials\"}"));
    }
}
