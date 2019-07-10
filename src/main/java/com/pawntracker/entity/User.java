package com.pawntracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawntracker.entity.id.Identification;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Address> addressList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "users", orphanRemoval = true)
    private List<PhoneNumber> phoneNumberList = new ArrayList<>();


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="identification_id",nullable = false)
    @JsonIgnore
    private Identification identification;


    private String username;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }


}
