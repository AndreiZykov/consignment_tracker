package com.pawntracker.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Address line is required")
    private String firstLine;

    private String secondLine;

    @NotBlank(message = "City is required")
    @Size(max = 30, message = "Wrong city name")
    private String city;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "State is required")
    @Size(max = 2, min = 2, message = "Wrong zipcode")
    private String state;

    @NotBlank(message = "Zipcode is required")
    @Size(max = 6, min = 6,  message = "Wrong zipcode")
    private String zip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_address_id", updatable = false, nullable = false)
    @JsonIgnore
    private User user;


    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
