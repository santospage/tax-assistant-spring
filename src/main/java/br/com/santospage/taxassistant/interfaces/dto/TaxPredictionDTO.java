package br.com.santospage.taxassistant.interfaces.dto;

public record TaxPredictionDTO(
        String taxCode,
        String descriptionTax,
        Double taxAliquot,
        Double probability,
        String level
) {
}


