package br.com.santospage.taxassistant.infrastructure.persistence;

import br.com.santospage.taxassistant.domain.models.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setCompany(rs.getString("B1_FILIAL"));
        product.setId(rs.getString("B1_COD"));
        product.setName(rs.getString("B1_DESC"));
        product.setTypeProduct(rs.getString("B1_TIPO"));
        product.setSpecifingCodeST(rs.getString("B1_CEST"));
        product.setUnitMeasure(rs.getString("B1_UM"));
        product.setUnitValue(rs.getDouble("B1_PRV1"));
        product.setStandarOutflowCode(rs.getString("B1_TS"));
        product.setStandarInflowCode(rs.getString("B1_TE"));
        product.setMercosulExtNomenclature(rs.getString("B1_POSIPI"));
        return product;
    }
}
