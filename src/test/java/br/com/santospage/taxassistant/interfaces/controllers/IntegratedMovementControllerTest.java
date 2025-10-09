package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.IntegratedMovementService;
import br.com.santospage.taxassistant.domain.exceptions.IntegratedMovementNotFoundException;
import br.com.santospage.taxassistant.domain.models.IntegratedMovement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class IntegratedMovementControllerTest {

    private IntegratedMovementService service;
    private IntegratedMovementController controller;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(IntegratedMovementService.class);
        controller = new IntegratedMovementController(service);
    }

    // ---------------------------
    // getAll()
    // ---------------------------

    @Test
    void getAll_shouldReturnOk_whenServiceReturnsList() {
        // Arrange
        IntegratedMovement movement = new IntegratedMovement(null, null, null);
        when(service.getAll()).thenReturn(List.of(movement));

        // Act
        ResponseEntity<?> response = controller.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
        verify(service, times(1)).getAll();
    }

    @Test
    void getAll_shouldReturnNotFound_whenServiceThrowsNotFoundException() {
        // Arrange
        when(service.getAll()).thenThrow(new IntegratedMovementNotFoundException("No records found."));

        // Act
        ResponseEntity<?> response = controller.getAll();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Not Found", body.get("error"));
        assertEquals("No records found.", body.get("message"));
        verify(service, times(1)).getAll();
    }

    @Test
    void getAll_shouldReturnInternalServerError_whenUnexpectedExceptionOccurs() {
        // Arrange
        when(service.getAll()).thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<?> response = controller.getAll();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Internal Server Error", body.get("error"));
        assertEquals("Unexpected error", body.get("message"));
        verify(service, times(1)).getAll();
    }

    // ---------------------------
    // getByCompany()
    // ---------------------------

    @Test
    void getByCompany_shouldReturnOk_whenServiceReturnsList() {
        // Arrange
        String company = "ABC";
        IntegratedMovement movement = new IntegratedMovement(null, null, null);
        when(service.getByCompany(company)).thenReturn(List.of(movement));

        // Act
        ResponseEntity<?> response = controller.getByCompany(company);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
        verify(service, times(1)).getByCompany(company);
    }

    @Test
    void getByCompany_shouldReturnNotFound_whenServiceThrowsNotFoundException() {
        // Arrange
        String company = "XYZ";
        when(service.getByCompany(company))
                .thenThrow(new IntegratedMovementNotFoundException("No records found for the company: XYZ"));

        // Act
        ResponseEntity<?> response = controller.getByCompany(company);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        Assertions.assertNotNull(body);
        assertEquals("Not Found", body.get("error"));
        assertEquals("No records found for the company: XYZ", body.get("message"));
        verify(service, times(1)).getByCompany(company);
    }

    @Test
    void getByCompany_shouldReturnInternalServerError_whenUnexpectedExceptionOccurs() {
        // Arrange
        String company = "DEF";
        when(service.getByCompany(company)).thenThrow(new RuntimeException("Unexpected failure"));

        // Act
        ResponseEntity<?> response = controller.getByCompany(company);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        Assertions.assertNotNull(body);
        assertEquals("Internal Server Error", body.get("error"));
        assertEquals("Unexpected failure", body.get("message"));
        verify(service, times(1)).getByCompany(company);
    }
}
