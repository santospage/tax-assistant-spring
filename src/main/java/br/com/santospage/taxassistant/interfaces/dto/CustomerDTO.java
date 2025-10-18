package br.com.santospage.taxassistant.interfaces.dto;

import br.com.santospage.taxassistant.domain.models.CustomerModel;

public class CustomerDTO {
    private final String company;
    private final String id;
    private final String name;
    private final String natureCustomer;
    private final String address;
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
        this.company = model.getCompany();
        this.id = model.getId();
        this.name = model.getName();
        this.natureCustomer = model.getNatureCustomer();
        this.address = model.getAddress();
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
    public String getCompany() {
        return company;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNatureCustomer() {
        return natureCustomer;
    }

    public String getAddress() {
        return address;
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