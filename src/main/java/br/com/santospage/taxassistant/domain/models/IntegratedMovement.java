package br.com.santospage.taxassistant.domain.models;

public record IntegratedMovement(
        String companyCode,
        String taxId,
        String descriptionTax) {

}