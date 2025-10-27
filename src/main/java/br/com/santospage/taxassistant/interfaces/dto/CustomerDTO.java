package br.com.santospage.taxassistant.interfaces.dto;

import br.com.santospage.taxassistant.domain.models.CustomerModel;

public class CustomerDTO {
    private final String companyCode;
    private final String customerId;
    private final String nameCustomer;
    private final String natureCustomer;
    private final String addressCustomer;
    private final String typeCustomer;
    private final String ufCustomer;
    private final String municipalCode;
    private final String cityCustomer;
    private final String neighborhoodCustomer;
    private final String zipCodeCustomer;
    private final String countryCustomer;
    private final String phoneCustomer;
    private final String nationalRegistryCustomer;
    private final String stateRegistrationCustomer;

    public CustomerDTO(CustomerModel model) {
        this.companyCode = model.getCompanyCode();
        this.customerId = model.getCustomerId();
        this.nameCustomer = model.getNameCustomer();
        this.natureCustomer = model.getNatureCustomer();
        this.addressCustomer = model.getAddressCustomer();
        this.typeCustomer = (model.getTypeCustomer() != null)
                ? model.getTypeCustomer() : "";
        this.ufCustomer = model.getUfCustomer();
        this.municipalCode = model.getMunicipalCode();
        this.cityCustomer = model.getCityCustomer();
        this.neighborhoodCustomer = model.getNeighborhoodCustomer();
        this.zipCodeCustomer = model.getZipCodeCustomer();
        this.countryCustomer = model.getCountryCustomer();
        this.phoneCustomer = model.getPhoneCustomer();
        this.nationalRegistryCustomer = model.getNationalRegistryCustomer();
        this.stateRegistrationCustomer = model.getStateRegistrationCustomer();
    }

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
}