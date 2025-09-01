package br.com.santospage.taxassistant.infrastructure.database;

import br.com.santospage.taxassistant.domain.entities.Customer;
import br.com.santospage.taxassistant.domain.enums.CustomerType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCompany(rs.getString("A1_FILIAL"));
        customer.setId(rs.getString("A1_COD"));
        customer.setName(rs.getString("A1_NOME"));
        customer.setNatureCustomer(rs.getString("A1_NATUREZ"));
        customer.setAddress(rs.getString("A1_END"));
        customer.setTypeCustomer(String.valueOf(CustomerType.valueOf(rs.getString("A1_TIPO"))));
        customer.setUfCustomer(rs.getString("A1_EST"));
        customer.setMunicipalCode(rs.getString("A1_COD_MUN"));
        customer.setCityCustomer(rs.getString("A1_MUN"));
        customer.setNeighborhoodCustomer(rs.getString("A1_BAIRRO"));
        customer.setCountryCustomer(rs.getString("A1_PAIS"));
        customer.setZipCodeCustomer(rs.getString("A1_CEP"));
        customer.setPhoneCustomer(rs.getString("A1_TEL"));
        customer.setCnpjCustomer(rs.getString("A1_CGC"));
        customer.setStateRegistry(rs.getString("A1_INSCR"));
        return customer;
    }
}
