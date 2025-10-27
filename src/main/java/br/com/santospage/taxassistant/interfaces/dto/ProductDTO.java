package br.com.santospage.taxassistant.interfaces.dto;

import br.com.santospage.taxassistant.domain.models.ProductModel;

public class ProductDTO {
    private final String companyCode;
    private final String productId;
    private final String nameProduct;
    private final String typeProduct;
    private final String specifingCodeST;
    private final String unitMeasure;
    private final Double unitValue;
    private final String standarOutflowCode;
    private final String standarInflowCode;
    private final String mercosulExtNomenclature;

    public ProductDTO(ProductModel model) {
        this.companyCode = model.getCompanyCode();
        this.productId = model.getProductId();
        this.nameProduct = model.getNameProduct();
        this.typeProduct = model.getTypeProduct();
        this.specifingCodeST = model.getSpecifingCodeST();
        this.unitMeasure = model.getUnitMeasure();
        this.unitValue = model.getUnitValue();
        this.standarOutflowCode = model.getStandarOutflowCode();
        this.standarInflowCode = model.getStandarInflowCode();
        this.mercosulExtNomenclature = model.getMercosulExtNomenclature();
    }

    // Getters
    public String getCompanyCode() {

        return companyCode;
    }

    public String getProductId() {
        return productId;
    }

    public String getNameProduct() {
        return nameProduct;
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
