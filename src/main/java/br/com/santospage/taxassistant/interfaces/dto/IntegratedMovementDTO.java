package br.com.santospage.taxassistant.interfaces.dto;

public record IntegratedMovementDTO(
        String companyCode,
        String taxId,
        String descriptionTax
) {
}
