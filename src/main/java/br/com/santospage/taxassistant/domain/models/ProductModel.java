package br.com.santospage.taxassistant.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SB1T10")
public class ProductModel {
    @Column(name = "B1_FILIAL")
    private String company;

    @Id
    @Column(name = "B1_COD")
    private String id;

    @Column(name = "B1_DESC")
    private String name;

    @Column(name = "B1_TIPO")
    private String typeProduct;

    @Column(name = "B1_CEST")
    private String specifingCodeST;

    @Column(name = "B1_UM")
    private String unitMeasure;

    @Column(name = "B1_PRV1")
    private Double unitValue;

    @Column(name = "B1_TS")
    private String standarOutflowCode;

    @Column(name = "B1_TE")
    private String standarInflowCode;

    @Column(name = "B1_POSIPI")
    private String mercosulExtNomenclature;

    @Column(name = "D_E_L_E_T_")
    private String deleted;

    // Getters
    public String getCompany() {
        return company;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public String getSpecifingCodeST() {
        return specifingCodeST;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public String getStandarOutflowCode() {
        return standarOutflowCode;
    }

    public String getStandarInflowCode() {
        return standarInflowCode;
    }

    public String getMercosulExtNomenclature() {
        return mercosulExtNomenclature;
    }

    public boolean isActive() {
        return " ".equals(this.deleted);
    }
}
