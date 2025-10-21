package br.com.santospage.taxassistant.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "F2DT10")
public class FiscalMovementModel implements Serializable {

    @Id
    @Column(name = "F2D_ID")
    private String movementId;

    @Column(name = "F2D_FILIAL")
    private String companyCode;

    @Column(name = "F2D_IDALIQ")
    private String taxId;

    @Column(name = "F2D_IDCAD")
    private String registrationId;

    @Column(name = "F2D_IDBASE")
    private String baseId;

    @Column(name = "F2D_IDREL")
    private String relationshipId;

    @Column(name = "F2D_TABELA")
    private String movementTable;

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

    @Column(name = "D_E_L_E_T_")
    private String deleted;

    // Getters
    public String getCompanyCode() {
        return companyCode;
    }

    public String getMovementId() {
        return movementId;
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

    public boolean isActive() {
        return " ".equals(this.deleted);
    }
}

