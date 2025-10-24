package br.com.santospage.taxassistant.interfaces.dto;

import br.com.santospage.taxassistant.domain.models.SalesMovementModel;

public class SalesMovementDTO {
    private final String companyCode;
    private final String taxId;
    private final String documentNumber;
    private final String documentSeries;
    private final String itemNumber;
    private final String productCode;
    private final Double quantity;
    private final Double unitValue;
    private final Double grandTotal;
    private final String customerCode;
    private final String customerUnit;
    
    public SalesMovementDTO(SalesMovementModel model) {
        this.taxId = model.getTaxId();
        this.companyCode = model.getCompanyCode();
        this.documentNumber = model.getDocumentNumber();
        this.documentSeries = model.getDocumentSeries();
        this.itemNumber = model.getItemNumber();
        this.productCode = model.getProductCode();
        this.quantity = model.getQuantity();
        this.unitValue = model.getUnitValue();
        this.grandTotal = model.getGrandTotal();
        this.customerCode = model.getCustomerCode();
        this.customerUnit = model.getCustomerUnit();
    }

    public String getTaxId() {
        return taxId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getDocumentSeries() {
        return documentSeries;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getCustomerUnit() {
        return customerUnit;
    }
}
