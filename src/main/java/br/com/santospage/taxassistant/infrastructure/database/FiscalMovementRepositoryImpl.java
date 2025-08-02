package br.com.santospage.taxassistant.infrastructure.database;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.domain.repositories.FiscalMovementRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FiscalMovementRepositoryImpl implements FiscalMovementRepository {

    private final JdbcTemplate jdbcTemplate;

    protected FiscalMovementRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FiscalMovement> findAll() {
        String sql =
                "SELECT F2D_FILIAL, F2D_IDREL, F2D_TABELA, F2D_TRIB " +
                        "FROM F2DT10 " +
                        "WHERE D_E_L_E_T_ = ' ' " +
                        "ORDER BY F2D_FILIAL";
        return jdbcTemplate.query(sql, new FiscalMovementRowMapper());
    }

    @Override
    public List<FiscalMovement> findByTable(String table) {
        String sql =
                "SELECT F2D_FILIAL, F2D_IDREL, F2D_TABELA, F2D_TRIB " +
                        "FROM F2DT10 " +
                        "WHERE F2D_TABELA = ? " +
                        "AND D_E_L_E_T_ = ' ' " +
                        "ORDER BY F2D_FILIAL, F2D_TABELA";

        return jdbcTemplate.query(sql, new FiscalMovementRowMapper(), table);
    }


    @Override
    public Optional<FiscalMovement> findById(String id) {
        String sql =
                "SELECT F2D_FILIAL, F2D_IDREL, F2D_TABELA, F2D_TRIB " +
                        "FROM F2DT10 " +
                        "WHERE F2D_IDREL = ? AND D_E_L_E_T_ = ' ' " +
                        "ORDER BY F2D_FILIAL, F2D_IDREL";
        List<FiscalMovement> result = jdbcTemplate.query(sql, new FiscalMovementRowMapper(), id);
        return result.stream().findFirst();
    }

    @Override
    public FiscalMovement save(FiscalMovement movement) {
        return null;
    }
}

