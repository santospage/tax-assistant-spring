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
    @Column(name = "F2D_ID")
    private String idMovement;
    @Column(name = "F2D_IDALIQ", nullable = true)
    private String idTax = "";
    @Column(name = "F2D_IDCAD", nullable = true)
    private String idRegistration = "";
    @Column(name = "F2D_IDBASE", nullable = true)
    private String idBase = "";
    @Column(name = "F2D_IDREL")
    private String idRelationship;
    @Column(name = "F2D_TABELA", nullable = true)
    private String tableMovement = "";
    @Column(name = "F2D_TRIB", nullable = true)
    private String taxCode = "";
    @Column(name = "F2D_BASE", nullable = true)
    private Double taxBase = 0.0;
    @Column(name = "F2D_BASQTD", nullable = true)
    private Double taxQuantity = 0.0;
    @Column(name = "F2D_ALIQ", nullable = true)
    private Double taxAliquot = 0.0;
    @Column(name = "F2D_VALOR", nullable = true)
    private Double taxValue = 0.0;
    @Column(name = "F2D_MVA", nullable = true)
    private Double taxValueMargin = 0.0;
    @Column(name = "F2D_PAUTA", nullable = true)
    private Double taxTariffValue = 0.0;
    @Column(name = "F2D_TRBMAJ", nullable = true)
    private String taxTributeAumented = "";
    @Column(name = "F2D_ALQMAJ", nullable = true)
    private Double taxAliquotAumented = 0.0;
    @Column(name = "F2D_MAJORA", nullable = true)
    private Double taxValueAumented = 0.0;

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

    public String getIdTax() {
        return idTax;
    }

    public void setIdTax(String idTax) {
        this.idTax = idTax;
    }

    public String getIdRegistration() {
        return idRegistration;
    }

    public void setIdRegistration(String idRegistration) {
        this.idRegistration = idRegistration;
    }

    public String getIdBase() {
        return idBase;
    }

    public void setIdBase(String idBase) {
        this.idBase = idBase;
    }

    public String getIdRelationship() {
        return idRelationship;
    }

    public void setIdRelationship(String idRelationship) {
        this.idRelationship = idRelationship;
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

