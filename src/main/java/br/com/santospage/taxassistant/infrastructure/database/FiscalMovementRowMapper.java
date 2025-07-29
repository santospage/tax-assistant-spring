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
        //movement.setEmissionDate(rs.getTimestamp("DATA_EMISSAO").toLocalDateTime());
        //movement.setProductCode(rs.getString("COD_PRODUTO"));
        //movement.setTotalValue(rs.getDouble("VALOR_TOTAL"));
        //movement.setCfop(rs.getString("CFOP"));
        return movement;
    }
}

