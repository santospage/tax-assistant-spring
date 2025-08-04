package br.com.santospage.taxassistant.infrastructure.database;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FiscalMovementRowMapper implements RowMapper<FiscalMovement> {
    @Override
    public FiscalMovement mapRow(ResultSet rs, int rowNum) throws SQLException {
        FiscalMovement movement = new FiscalMovement();
        movement.setCompanyCode(rs.getString("F2D_FILIAL"));
        movement.setMovementId(rs.getString("F2D_IDREL"));
        movement.setTable(rs.getString("F2D_TABELA"));
        movement.setTaxCode(rs.getString("F2D_TRIB"));
        movement.setTaxBase(rs.getDouble("F2D_BASE"));
        movement.setTaxQuantity(rs.getDouble("F2D_BASQTD"));
        movement.setTaxAliquot(rs.getDouble("F2D_ALIQ"));
        movement.setTaxValue(rs.getDouble("F2D_VALOR"));
        movement.setTaxValueMargin(rs.getDouble("F2D_MVA"));
        movement.setTaxTariffValue(rs.getDouble("F2D_PAUTA"));
        movement.setTaxTributeAumented(rs.getString("F2D_TRBMAJ"));
        movement.setTaxAliquotAumented(rs.getDouble("F2D_ALQMAJ"));
        movement.setTaxValueAumented(rs.getDouble("F2D_MAJORA"));
        return movement;
    }
}

