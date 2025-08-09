package br.com.santospage.taxassistant.domain.entities;

public class Product {
    private String company;
    private String id;
    private String name;
    private String typeProduct;
    private String specifingCodeST;
    private String unitMeasure;
    private Double unitValue;
    private String standarOutflowCode;
    private String standarInflowCode;
    private String mercosulExtNomenclature;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
