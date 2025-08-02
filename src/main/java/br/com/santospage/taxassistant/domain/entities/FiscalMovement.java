package br.com.santospage.taxassistant.domain.entities;

public class FiscalMovement {
    private String company;
    private String idMovement;
    private String tableMovement;
    private String TaxCode;
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
        return TaxCode;
    }

    public void setTaxCode(String taxCode) {
        TaxCode = taxCode;
    }
}

