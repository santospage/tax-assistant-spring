package br.com.santospage.taxassistant.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private final String secret = "my-very-secret-key-which-is-long-enough";
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(secret);
    }

    @Test
    void createToken_shouldReturnValidToken() {
        String token = jwtTokenProvider.createToken("user01", "ADMIN");
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        assertEquals("user01", jwtTokenProvider.getUsername(token));
    }

    @Test
    void validateToken_shouldReturnFalseForInvalidToken() {
        String invalidToken = "this.is.not.a.valid.token";
        assertFalse(jwtTokenProvider.validateToken(invalidToken));
    }

    @Test
    void getUsername_shouldReturnCorrectUsername() {
        String token = jwtTokenProvider.createToken("user02", "USER");
        String username = jwtTokenProvider.getUsername(token);
        assertEquals("user02", username);
    }
}
