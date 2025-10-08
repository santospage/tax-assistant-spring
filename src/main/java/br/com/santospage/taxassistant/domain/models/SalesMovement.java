package br.com.santospage.taxassistant.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "SD2T10")
@SQLRestriction("TRIM(D_E_L_E_T_) = ' '")
public class SalesMovement {
    @Column(name = "D2_FILIAL")
    private String filial;
    @Id
    @Column(name = "D2_IDTRIB", nullable = true)
    private String idTax = "";
    @Column(name = "D2_DOC", nullable = true)
    private String documentNumber = "";
    @Column(name = "D2_SERIE", nullable = true)
    private String documentSeries = "";
    @Column(name = "D2_ITEM", nullable = true)
    private String itemNumber = "";
    @Column(name = "D2_COD", nullable = true)
    private String productCode = "";
    @Column(name = "D2_QUANT", nullable = true)
    private Double quantity = 0.0;
    @Column(name = "D2_PRCVEN", nullable = true)
    private Double unitValue = 0.0;
    @Column(name = "D2_TOTAL", nullable = true)
    private Double grandTotal = 0.0;
    @Column(name = "D2_CLIENTE", nullable = true)
    private String customerCode = "";
    @Column(name = "D2_LOJA", nullable = true)
    private String customerUnit = "";

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getIdTax() {
        return idTax;
    }

    public void setIdTax(String idTax) {
        this.idTax = idTax;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentSeries() {
        return documentSeries;
    }

    public void setDocumentSeries(String documentSeries) {
        this.documentSeries = documentSeries;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerUnit() {
        return customerUnit;
    }

    public void setCustomerUnit(String customerUnit) {
        this.customerUnit = customerUnit;
    }
}
