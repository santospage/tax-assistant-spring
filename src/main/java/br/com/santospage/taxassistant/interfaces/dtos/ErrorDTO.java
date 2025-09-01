package br.com.santospage.taxassistant.interfaces.dtos;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        int status,
        LocalDateTime timestamp,
        String path
) {
}
