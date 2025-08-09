package br.com.santospage.taxassistant.infrastructure.database;

import br.com.santospage.taxassistant.domain.entities.Customer;
import br.com.santospage.taxassistant.domain.repositories.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> findAll() {
        String sql =
                "SELECT A1_FILIAL, A1_COD, A1_NOME, A1_NATUREZ, " +
                "A1_END, A1_TIPO, A1_EST, A1_COD_MUN, A1_MUN, " +
                "A1_BAIRRO, A1_PAIS, A1_CEP, A1_TEL, A1_CGC, A1_INSCR " +
                "FROM SA1T10 " +
                "WHERE A1_COD <> ' ' AND D_E_L_E_T_ = ' ' " +
                "ORDER BY A1_FILIAL, A1_COD";

        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    @Override
    public Optional<Customer> findById(String id) {
        String sql =
                "SELECT A1_FILIAL, A1_COD, A1_NOME, A1_NATUREZ, " +
                "A1_END, A1_TIPO, A1_EST, A1_COD_MUN, A1_MUN, " +
                "A1_BAIRRO, A1_PAIS, A1_CEP, A1_TEL, A1_CGC, A1_INSCR " +
                "FROM SA1T10 " +
                "WHERE A1_COD = ? AND D_E_L_E_T_ = ' ' " +
                "ORDER BY A1_FILIAL, A1_COD";
        List<Customer> result = jdbcTemplate.query(sql, new CustomerRowMapper(), id);
        return result.stream().findFirst();
    }

}
