package com.pawntracker.entity.id;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawntracker.entity.Item;
import com.pawntracker.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "identification")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "identification", orphanRemoval = true)
    private List<DreversLicense> driversLicenseList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "identification", orphanRemoval = true)
    private List<IdentificationCard> identificationCardList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "identification", orphanRemoval = true)
    private List<Passport> passportList = new ArrayList<>();




    public Identification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DreversLicense> getDriversLicenseList() {
        return driversLicenseList;
    }

    public void setDriversLicenseList(List<DreversLicense> driversLicenseList) {
        this.driversLicenseList = driversLicenseList;
    }

    public List<IdentificationCard> getIdentificationCardList() {
        return identificationCardList;
    }

    public void setIdentificationCardList(List<IdentificationCard> identificationCardList) {
        this.identificationCardList = identificationCardList;
    }

    public List<Passport> getPassportList() {
        return passportList;
    }

    public void setPassportList(List<Passport> passportList) {
        this.passportList = passportList;
    }
}
