package br.com.santospage.taxassistant.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "SA1T10")
public class CustomerModel implements Serializable {

    @Id
    @Column(name = "A1_COD")
    private String customerId;

    @Column(name = "A1_FILIAL")
    private String companyCode;

    @Column(name = "A1_NOME")
    private String nameCustomer;

    @Column(name = "A1_NATUREZ")
    private String natureCustomer;

    @Column(name = "A1_END")
    private String addressCustomer;

    @Column(name = "A1_TIPO")
    private String typeCustomer;

    @Column(name = "A1_EST")
    private String ufCustomer;

    @Column(name = "A1_COD_MUN")
    private String municipalCode;

    @Column(name = "A1_MUN")
    private String cityCustomer;

    @Column(name = "A1_BAIRRO")
    private String neighborhoodCustomer;

    @Column(name = "A1_CEP")
    private String zipCodeCustomer;

    @Column(name = "A1_PAIS")
    private String countryCustomer;

    @Column(name = "A1_TEL")
    private String phoneCustomer;

    @Column(name = "A1_CGC")
    private String nationalRegistryCustomer;

    @Column(name = "A1_INSCR")
    private String stateRegistrationCustomer;

    @Column(name = "D_E_L_E_T_")
    private String deleted;

    // Getters
    public String getCompanyCode() {
        return companyCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public String getNatureCustomer() {
        return natureCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public String getTypeCustomer() {
        return typeCustomer;
    }

    public String getUfCustomer() {
        return ufCustomer;
    }

    public String getMunicipalCode() {
        return municipalCode;
    }

    public String getCityCustomer() {
        return cityCustomer;
    }

    public String getNeighborhoodCustomer() {
        return neighborhoodCustomer;
    }

    public String getZipCodeCustomer() {
        return zipCodeCustomer;
    }

    public String getCountryCustomer() {
        return countryCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public String getNationalRegistryCustomer() {
        return nationalRegistryCustomer;
    }

    public String getStateRegistrationCustomer() {
        return stateRegistrationCustomer;
    }

    public boolean isActive() {
        return " ".equals(this.deleted);
    }
}
