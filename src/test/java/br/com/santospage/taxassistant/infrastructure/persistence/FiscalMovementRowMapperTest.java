package br.com.santospage.taxassistant.infrastructure.persistence;

import br.com.santospage.taxassistant.domain.models.FiscalMovement;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FiscalMovementRowMapperTest {

    @Test
    void testMapRow() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getString("F2D_FILIAL")).thenReturn("010101");
        when(rs.getString("F2D_IDREL")).thenReturn("ID123");
        when(rs.getString("F2D_TABELA")).thenReturn("TabelaX");
        when(rs.getString("F2D_TRIB")).thenReturn("ICMS");
        when(rs.getDouble("F2D_BASE")).thenReturn(1000.50);
        when(rs.getDouble("F2D_BASQTD")).thenReturn(5.0);
        when(rs.getDouble("F2D_ALIQ")).thenReturn(18.0);
        when(rs.getDouble("F2D_VALOR")).thenReturn(180.09);
        when(rs.getDouble("F2D_MVA")).thenReturn(10.0);
        when(rs.getDouble("F2D_PAUTA")).thenReturn(200.0);
        when(rs.getString("F2D_TRBMAJ")).thenReturn("Augmented");
        when(rs.getDouble("F2D_ALQMAJ")).thenReturn(20.0);
        when(rs.getDouble("F2D_MAJORA")).thenReturn(210.0);

        FiscalMovementRowMapper mapper = new FiscalMovementRowMapper();

        FiscalMovement movement = mapper.mapRow(rs, 1);

        assert movement != null;
        assertEquals("010101", movement.getCompanyCode());
        assertEquals("ID123", movement.getMovementId());
        assertEquals("TabelaX", movement.getTable());
        assertEquals("ICMS", movement.getTaxCode());
        assertEquals(1000.50, movement.getTaxBase());
        assertEquals(5.0, movement.getTaxQuantity());
        assertEquals(18.0, movement.getTaxAliquot());
        assertEquals(180.09, movement.getTaxValue());
        assertEquals(10.0, movement.getTaxValueMargin());
        assertEquals(200.0, movement.getTaxTariffValue());
        assertEquals("Augmented", movement.getTaxTributeAumented());
        assertEquals(20.0, movement.getTaxAliquotAumented());
        assertEquals(210.0, movement.getTaxValueAumented());
    }
}

