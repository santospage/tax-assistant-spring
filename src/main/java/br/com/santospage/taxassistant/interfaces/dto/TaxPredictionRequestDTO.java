package br.com.santospage.taxassistant.interfaces.dto;

import br.com.santospage.taxassistant.domain.enums.CustomerType;

public record TaxPredictionRequestDTO(
        CustomerType customerType,
        String productType
) {
}
