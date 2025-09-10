package br.com.santospage.taxassistant.domain.models;

public class FiscalMovement {
    private String company;
    private String idMovement;
    private String tableMovement;
    private String taxCode;
    private Double taxBase;
    private Double taxQuantity;
    private Double taxAliquot;
    private Double taxValue;
    private Double taxValueMargin;
    private Double taxTariffValue;
    private String taxTributeAumented;
    private Double taxAliquotAumented;
    private Double taxValueAumented;

    public String getCompanyCode() {
        return company;
    }

    public void setCompanyCode(String companyCode) {
        this.company = companyCode;
    }

    public String getMovementId() {
        return idMovement;
    }

    public void setMovementId(String movementId) {
        this.idMovement = movementId;
    }

    public String getTable() {
        return tableMovement;
    }

    public void setTable(String table) {
        this.tableMovement = table;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Double getTaxBase() {
        return taxBase;
    }

    public void setTaxBase(Double taxBase) {
        this.taxBase = taxBase;
    }

    public Double getTaxQuantity() {
        return taxQuantity;
    }

    public void setTaxQuantity(Double taxQuantity) {
        this.taxQuantity = taxQuantity;
    }

    public Double getTaxAliquot() {
        return taxAliquot;
    }

    public void setTaxAliquot(Double taxAliquot) {
        this.taxAliquot = taxAliquot;
    }

    public Double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    public Double getTaxValueMargin() {
        return taxValueMargin;
    }

    public void setTaxValueMargin(Double taxValueMargin) {
        this.taxValueMargin = taxValueMargin;
    }

    public Double getTaxTariffValue() {
        return taxTariffValue;
    }

    public void setTaxTariffValue(Double taxTariffValue) {
        this.taxTariffValue = taxTariffValue;
    }

    public String getTaxTributeAumented() {
        return taxTributeAumented;
    }

    public void setTaxTributeAumented(String taxTributeAumented) {
        this.taxTributeAumented = taxTributeAumented;
    }

    public Double getTaxAliquotAumented() {
        return taxAliquotAumented;
    }

    public void setTaxAliquotAumented(Double taxAliquotAumented) {
        this.taxAliquotAumented = taxAliquotAumented;
    }

    public Double getTaxValueAumented() {
        return taxValueAumented;
    }

    public void setTaxValueAumented(Double taxValueAumented) {
        this.taxValueAumented = taxValueAumented;
    }
}

