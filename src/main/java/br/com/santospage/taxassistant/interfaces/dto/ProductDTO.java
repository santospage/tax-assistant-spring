package br.com.santospage.taxassistant.interfaces.dto;

import br.com.santospage.taxassistant.domain.models.ProductModel;

public class ProductDTO {
    private final String company;
    private final String id;
    private final String name;
    private final String typeProduct;
    private final String specifingCodeST;
    private final String unitMeasure;
    private final Double unitValue;
    private final String standarOutflowCode;
    private final String standarInflowCode;
    private final String mercosulExtNomenclature;

    public ProductDTO(ProductModel model) {
        this.company = model.getCompany();
        this.id = model.getId();
        this.name = model.getName();
        this.typeProduct = model.getTypeProduct();
        this.specifingCodeST = model.getSpecifingCodeST();
        this.unitMeasure = model.getUnitMeasure();
        this.unitValue = model.getUnitValue();
        this.standarOutflowCode = model.getStandarOutflowCode();
        this.standarInflowCode = model.getStandarInflowCode();
        this.mercosulExtNomenclature = model.getMercosulExtNomenclature();
    }

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
}
