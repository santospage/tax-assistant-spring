package br.com.santospage.taxassistant.infrastructure.persistence;

import br.com.santospage.taxassistant.domain.models.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductRowMapperTest {

    @Test
    void testMapRow() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getString("B1_FILIAL")).thenReturn("010101");
        when(rs.getString("B1_COD")).thenReturn("PRD001");
        when(rs.getString("B1_DESC")).thenReturn("NOTEBOOK GAMER");
        when(rs.getString("B1_TIPO")).thenReturn("PA");
        when(rs.getString("B1_CEST")).thenReturn("1234567");
        when(rs.getString("B1_UM")).thenReturn("UN");
        when(rs.getDouble("B1_PRV1")).thenReturn(4999.99);
        when(rs.getString("B1_TS")).thenReturn("501");
        when(rs.getString("B1_TE")).thenReturn("001");
        when(rs.getString("B1_POSIPI")).thenReturn("85423900");

        ProductRowMapper mapper = new ProductRowMapper();

        Product product = mapper.mapRow(rs, 1);

        assert product != null;
        assertEquals("010101", product.getCompany());
        assertEquals("PRD001", product.getId());
        assertEquals("NOTEBOOK GAMER", product.getName());
        assertEquals("PA", product.getTypeProduct());
        assertEquals("1234567", product.getSpecifingCodeST());
        assertEquals("UN", product.getUnitMeasure());
        assertEquals(4999.99, product.getUnitValue());
        assertEquals("501", product.getStandarOutflowCode());
        assertEquals("001", product.getStandarInflowCode());
        assertEquals("85423900", product.getMercosulExtNomenclature());
    }
}


