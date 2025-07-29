package br.com.santospage.taxassistant.domain.entities;

public class FiscalMovement {
    private String f2dfilial;
    private String f2dIdrel;
    private String f2dtable;
    //private String f2dtrib;
    //private Double f2dbase;
    //private Double f2dbasqtd;
    //private Double f2daliq;
    //private Double f2dvalor;
    //private Double f2daliqtr;
    //private Double f2dmva;
    //private Double f2dauxmva;
    //private Double f2dpauta;
    //private Double f2dmajora;
    //private Double f2dauxmaj;
    //private Double f2dtrbmaj;
    //private Double f2dvalmaj;
    //private Double f2dalqmaj;

    public String getCompanyCode() {
        return f2dfilial;
    }

    public void setCompanyCode(String companyCode) {
        this.f2dfilial = companyCode;
    }

    public String getMovementId() {
        return f2dIdrel;
    }

    public void setMovementId(String movementId) {
        this.f2dIdrel = movementId;
    }

    public String getTable() {
        return f2dtable;
    }

    public void setTable(String table) {
        this.f2dtable = table;
    }

    /*
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
    */
}

