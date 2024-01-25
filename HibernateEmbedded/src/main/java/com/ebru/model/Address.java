package com.ebru.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "COUNTRY", length = 100)
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "DISTRICT")
    private String district;


    public Address() {
    }

    public Address(String city, String district, String mahalle) {
        this.city = city;
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
