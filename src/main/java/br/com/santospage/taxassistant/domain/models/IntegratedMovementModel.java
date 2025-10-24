package br.com.santospage.taxassistant.domain.models;

public record IntegratedMovementModel(
        String companyCode,
        String taxId,
        String descriptionTax) {

}