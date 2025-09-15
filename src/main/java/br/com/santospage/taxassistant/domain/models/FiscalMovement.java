package br.com.santospage.taxassistant.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "F2DT10")
@SQLRestriction("TRIM(D_E_L_E_T_) = ' '")
public class FiscalMovement {
    @Column(name = "F2D_FILIAL")
    private String filial;
    @Id
    @Column(name = "F2D_IDREL")
    private String idMovement;
    @Column(name = "F2D_TABELA")
    private String tableMovement;
    @Column(name = "F2D_TRIB")
    private String taxCode;
    @Column(name = "F2D_BASE")
    private Double taxBase;
    @Column(name = "F2D_BASQTD")
    private Double taxQuantity;
    @Column(name = "F2D_ALIQ")
    private Double taxAliquot;
    @Column(name = "F2D_VALOR")
    private Double taxValue;
    @Column(name = "F2D_MVA")
    private Double taxValueMargin;
    @Column(name = "F2D_PAUTA")
    private Double taxTariffValue;
    @Column(name = "F2D_TRBMAJ")
    private String taxTributeAumented;
    @Column(name = "F2D_ALQMAJ")
    private Double taxAliquotAumented;
    @Column(name = "F2D_MAJORA")
    private Double taxValueAumented;

    public String getCompanyCode() {
        return filial;
    }

    public void setCompanyCode(String companyCode) {
        this.filial = companyCode;
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

