package ch.zli.m223.ksh18a.crm.controller;

import ch.zli.m223.ksh18a.crm.model.AppCustomer;

public class CustomerDto {

    public Long id = null;

    public String name = "";
    public String street= "";
    public String city= "";

    public CustomerDto() {}

    public CustomerDto(AppCustomer customer) {
        if (customer == null) { return; }
        id = customer.getId();
        name = customer.getName();
        street = customer.getStreet();
        city = customer.getCity();
    }
}
