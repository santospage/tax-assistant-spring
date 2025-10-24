package br.com.santospage.taxassistant.interfaces.dto;

import br.com.santospage.taxassistant.domain.models.FiscalMovementModel;

public class FiscalMovementDTO {
    private final String movementId;
    private final String companyCode;
    private final String taxId;
    private final String registrationId;
    private final String baseId;
    private final String relationshipId;
    private final String movementTable;
    private final String taxCode;
    private final Double taxBase;
    private final Double taxQuantity;
    private final Double taxAliquot;
    private final Double taxValue;
    private final Double taxValueMargin;
    private final Double taxTariffValue;
    private final String taxTributeAumented;
    private final Double taxAliquotAumented;
    private final Double taxValueAumented;

    public FiscalMovementDTO(FiscalMovementModel model) {
        this.movementId = model.getMovementId();
        this.companyCode = model.getCompanyCode();
        this.taxId = model.getTaxId();
        this.registrationId = model.getRegistrationId();
        this.baseId = model.getBaseId();
        this.relationshipId = model.getRelationshipId();
        this.movementTable = model.getMovementTable();
        this.taxCode = model.getTaxCode();
        this.taxBase = model.getTaxBase();
        this.taxQuantity = model.getTaxQuantity();
        this.taxAliquot = model.getTaxAliquot();
        this.taxValue = model.getTaxValue();
        this.taxValueMargin = model.getTaxValueMargin();
        this.taxTariffValue = model.getTaxTariffValue();
        this.taxTributeAumented = model.getTaxTributeAumented();
        this.taxAliquotAumented = model.getTaxAliquotAumented();
        this.taxValueAumented = model.getTaxValueAumented();
    }
    
    public String getMovementId() {
        return movementId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getBaseId() {
        return baseId;
    }

    public String getRelationshipId() {
        return relationshipId;
    }

    public String getMovementTable() {
        return movementTable;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public Double getTaxBase() {
        return taxBase;
    }

    public Double getTaxQuantity() {
        return taxQuantity;
    }

    public Double getTaxAliquot() {
        return taxAliquot;
    }

    public Double getTaxValue() {
        return taxValue;
    }

    public Double getTaxValueMargin() {
        return taxValueMargin;
    }

    public Double getTaxTariffValue() {
        return taxTariffValue;
    }

    public String getTaxTributeAumented() {
        return taxTributeAumented;
    }

    public Double getTaxAliquotAumented() {
        return taxAliquotAumented;
    }

    public Double getTaxValueAumented() {
        return taxValueAumented;
    }
}
