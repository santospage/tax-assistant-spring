package br.com.santospage.taxassistant.infrastructure.database;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FiscalMovementRepositoryImpl implements FiscalMovementRepository {

    private final JdbcTemplate jdbcTemplate;

    public FiscalMovementRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FiscalMovement> findByTable(String tableCode) {
        String sql = "SELECT F2D_ID FROM F2DT10 WHERE F2D_TABELA = ? AND D_E_L_E_T_ = ' '";
        return jdbcTemplate.query(sql, new FiscalMovementRowMapper(), tableCode);
    }
}

