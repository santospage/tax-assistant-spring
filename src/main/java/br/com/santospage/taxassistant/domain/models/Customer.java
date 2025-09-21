package br.com.santospage.taxassistant.domain.models;

import br.com.santospage.taxassistant.domain.enums.CustomerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "SA1T10")
@SQLRestriction("TRIM(D_E_L_E_T_) = ' '")
public class Customer {

    @Column(name = "A1_FILIAL")
    private String filial;
    @Id
    @Column(name = "A1_COD")
    private String id;
    @Column(name = "A1_NOME")
    private String name;
    @Column(name = "A1_NATUREZ", nullable = true)
    private String natureCustomer = "";
    @Column(name = "A1_END", nullable = true)
    private String address = "";
    @Column(name = "A1_TIPO")
    private String typeCustomer;
    @Column(name = "A1_EST", nullable = true)
    private String ufCustomer = "";
    @Column(name = "A1_COD_MUN", nullable = true)
    private String municipalCode;
    @Column(name = "A1_MUN", nullable = true)
    private String cityCustomer = "";
    @Column(name = "A1_BAIRRO", nullable = true)
    private String neighborhoodCustomer = "";
    @Column(name = "A1_CEP", nullable = true)
    private String zipCodeCustomer = "";
    @Column(name = "A1_PAIS", nullable = true)
    private String countryCustomer = "";
    @Column(name = "A1_TEL", nullable = true)
    private String phoneCustomer = "";
    @Column(name = "A1_CGC", nullable = true)
    private String nationalRegistryCustomer = "";
    @Column(name = "A1_INSCR", nullable = true)
    private String stateRegistrationCustomer = "";

    // Getters and Setters
    public String getCompany() {
        return filial;
    }

    public void setCompany(String company) {
        this.filial = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNatureCustomer() {
        return natureCustomer;
    }

    public void setNatureCustomer(String natureCustomer) {
        this.natureCustomer = natureCustomer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerType getTypeCustomer() {
        return CustomerType.valueOf(typeCustomer);
    }

    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public String getUfCustomer() {
        return ufCustomer;
    }

    public void setUfCustomer(String ufCustomer) {
        this.ufCustomer = ufCustomer;
    }

    public String getMunicipalCode() {
        return municipalCode;
    }

    public void setMunicipalCode(String municipalCode) {
        this.municipalCode = municipalCode;
    }

    public String getCityCustomer() {
        return cityCustomer;
    }

    public void setCityCustomer(String cityCustomer) {
        this.cityCustomer = cityCustomer;
    }

    public String getNeighborhoodCustomer() {
        return neighborhoodCustomer;
    }

    public void setNeighborhoodCustomer(String neighborhoodCustomer) {
        this.neighborhoodCustomer = neighborhoodCustomer;
    }

    public String getZipCodeCustomer() {
        return zipCodeCustomer;
    }

    public void setZipCodeCustomer(String zipCodeCustomer) {
        this.zipCodeCustomer = zipCodeCustomer;
    }

    public String getCountryCustomer() {
        return countryCustomer;
    }

    public void setCountryCustomer(String countryCustomer) {
        this.countryCustomer = countryCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getNationalRegistryCustomer() {
        return nationalRegistryCustomer;
    }

    public void setNationalRegistryCustomer(String nationalRegistryCustomer) {
        this.nationalRegistryCustomer = nationalRegistryCustomer;
    }

    public String getStateRegistrationCustomer() {
        return stateRegistrationCustomer;
    }

    public void setStateRegistrationCustomer(String stateRegistrationCustomer) {
        this.stateRegistrationCustomer = stateRegistrationCustomer;
    }
}
