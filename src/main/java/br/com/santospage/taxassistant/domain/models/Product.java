package br.com.santospage.taxassistant.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "SB1T10")
@SQLRestriction("TRIM(D_E_L_E_T_) = ' '")
public class Product {
    @Column(name = "B1_FILIAL")
    private String filial;
    @Id
    @Column(name = "B1_COD")
    private String id;
    @Column(name = "B1_DESC", nullable = true)
    private String name = "";
    @Column(name = "B1_TIPO", nullable = true)
    private String typeProduct = "";
    @Column(name = "B1_CEST", nullable = true)
    private String specifingCodeST = "";
    @Column(name = "B1_UM", nullable = true)
    private String unitMeasure = "";
    @Column(name = "B1_PRV1", nullable = true)
    private Double unitValue = 0.0;
    @Column(name = "B1_TS", nullable = true)
    private String standarOutflowCode = "";
    @Column(name = "B1_TE", nullable = true)
    private String standarInflowCode = "";
    @Column(name = "B1_POSIPI", nullable = true)
    private String mercosulExtNomenclature = "";

    public String getCompany() {
        return filial;
    }

    public void setCompany(String company) {
        this.filial = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getSpecifingCodeST() {
        return specifingCodeST;
    }

    public void setSpecifingCodeST(String specifingCodeST) {
        this.specifingCodeST = specifingCodeST;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

    public String getStandarOutflowCode() {
        return standarOutflowCode;
    }

    public void setStandarOutflowCode(String standarOutflowCode) {
        this.standarOutflowCode = standarOutflowCode;
    }

    public String getStandarInflowCode() {
        return standarInflowCode;
    }

    public void setStandarInflowCode(String standarInflowCode) {
        this.standarInflowCode = standarInflowCode;
    }

    public String getMercosulExtNomenclature() {
        return mercosulExtNomenclature;
    }

    public void setMercosulExtNomenclature(String mercosulExtNomenclature) {
        this.mercosulExtNomenclature = mercosulExtNomenclature;
    }
}
