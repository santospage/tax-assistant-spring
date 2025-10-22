package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.services.IntegratedMovementService;
import br.com.santospage.taxassistant.interfaces.dto.IntegratedMovementDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IntegratedMovementModelControllerTest {

    private IntegratedMovementService service;
    private IntegratedMovementController controller;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(IntegratedMovementService.class);
        controller = new IntegratedMovementController(service);
    }

    @Test
    void getAll_shouldReturnOk_whenServiceReturnsList() {
        // Arrange
        IntegratedMovementDTO dto = new IntegratedMovementDTO(
                "001",
                "TX123",
                "Description 1"
        );

        when(service.getAll()).thenReturn(Collections.singletonList(dto));

        // Act
        ResponseEntity<?> response = controller.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(List.class, response.getBody());

        @SuppressWarnings("unchecked")
        List<IntegratedMovementDTO> body = (List<IntegratedMovementDTO>) response.getBody();
        assertEquals(1, body.size());

        IntegratedMovementDTO returnedDto = body.getFirst();
        assertEquals("001", returnedDto.companyCode());
        assertEquals("TX123", returnedDto.taxId());
        assertEquals("Description 1", returnedDto.descriptionTax());

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
        assertNotNull(response.getBody());
        assertInstanceOf(Map.class, response.getBody());

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.get("status"));
        assertEquals("Internal Server Error", body.get("error"));
        assertEquals("Unexpected error", body.get("message"));

        verify(service, times(1)).getAll();
    }

    @Test
    void getByCompany_shouldReturnOk_whenServiceReturnsList() {
        // Arrange
        String company = "ABC";
        IntegratedMovementDTO dto = new IntegratedMovementDTO(
                "ABC",
                "TX456",
                "Description 2"
        );

        when(service.getByCompany(company)).thenReturn(Collections.singletonList(dto));

        // Act
        ResponseEntity<?> response = controller.getByCompany(company);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(List.class, response.getBody());

        @SuppressWarnings("unchecked")
        List<IntegratedMovementDTO> body = (List<IntegratedMovementDTO>) response.getBody();
        assertEquals(1, body.size());

        IntegratedMovementDTO returnedDto = body.getFirst();
        assertEquals("ABC", returnedDto.companyCode());
        assertEquals("TX456", returnedDto.taxId());
        assertEquals("Description 2", returnedDto.descriptionTax());

        verify(service, times(1)).getByCompany(company);
    }

    @Test
    void getByCompany_shouldReturnInternalServerError_whenUnexpectedExceptionOccurs() {
        // Arrange
        String company = "DEF";
        when(service.getByCompany(company))
                .thenThrow(new RuntimeException("Unexpected failure"));

        // Act
        ResponseEntity<?> response = controller.getByCompany(company);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(Map.class, response.getBody());

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.get("status"));
        assertEquals("Internal Server Error", body.get("error"));
        assertEquals("Unexpected failure", body.get("message"));

        verify(service, times(1)).getByCompany(company);
    }
}
