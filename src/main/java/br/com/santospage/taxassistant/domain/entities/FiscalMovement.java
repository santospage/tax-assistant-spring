package br.com.santospage.taxassistant.domain.entities;

import java.time.LocalDateTime;

public class FiscalMovement {
    private String f2dId;
    private String f2dtable;
    private String companyCode;
    private LocalDateTime emissionDate;
    private String productCode;
    private Double totalValue;
    private String cfop;

    public String getMovementId() {
        return f2dId;
    }

    public void setMovementId(String f2dId) {
        this.f2dId = f2dId;
    }

    public String getTable() {
        return f2dtable;
    }

    public void setTable(String f2DTabela) {
        this.f2dtable = f2DTabela;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public LocalDateTime getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDateTime emissionDate) {
        this.emissionDate = emissionDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }
}

