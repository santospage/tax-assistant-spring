package br.com.santospage.taxassistant.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "SD2T10")
public class SalesMovementModel implements Serializable {

    @Id
    @Column(name = "D2_IDTRIB")
    private String taxId;

    @Column(name = "D2_FILIAL")
    private String companyCode;

    @Column(name = "D2_DOC")
    private String documentNumber;

    @Column(name = "D2_SERIE")
    private String documentSeries;

    @Column(name = "D2_ITEM")
    private String itemNumber;

    @Column(name = "D2_COD")
    private String productCode;

    @Column(name = "D2_QUANT")
    private Double quantity;

    @Column(name = "D2_PRCVEN")
    private Double unitValue;

    @Column(name = "D2_TOTAL")
    private Double grandTotal;

    @Column(name = "D2_CLIENTE")
    private String customerCode;

    @Column(name = "D2_LOJA")
    private String customerUnit;

    @Column(name = "D_E_L_E_T_")
    private String deleted;

    // Getters
    public String getCompanyCode() {
        return companyCode;
    }

    public String getTaxId() {
        return taxId;
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

    public boolean isActive() {
        return " ".equals(this.deleted);
    }
}
