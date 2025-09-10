package br.com.santospage.taxassistant.infrastructure.persistence;

import br.com.santospage.taxassistant.domain.models.Product;
import br.com.santospage.taxassistant.domain.repositories.ProductRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    protected ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql =
                "SELECT B1_FILIAL, B1_COD, B1_DESC, B1_TIPO, " +
                "B1_CEST, B1_UM, B1_PRV1, B1_TS, " +
                "B1_TE, B1_POSIPI " +
                "FROM SB1T10 " +
                "WHERE D_E_L_E_T_ = ' ' " +
                "ORDER BY B1_FILIAL, B1_COD";

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public Optional<Product> findById(String id) {
        String sql =
                "SELECT B1_FILIAL, B1_COD, B1_DESC, B1_TIPO, " +
                "B1_CEST, B1_UM, B1_PRV1, B1_TS, " +
                "B1_TE, B1_POSIPI " +
                "FROM SB1T10 " +
                "WHERE B1_COD = ? AND D_E_L_E_T_ = ' ' " +
                "ORDER BY B1_FILIAL, B1_COD";
        List<Product> result = jdbcTemplate.query(sql, new ProductRowMapper(), id);
        return result.stream().findFirst();
    }
}
