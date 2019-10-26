package com.pawntracker.entity.id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class DriversLicense {

    public DriversLicense() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="identification_dl_id", nullable = false)
    @JsonIgnore
    private Identification identification;

    @NotNull(message = "Date Of Birth should'nt be blank")
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date dateOfBirth;

    @NotNull(message = "Date Of Expiration should'nt be blank")
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date dateOfExpiration;

    @NotBlank(message = "First Name should'nt be blank")
    private String firstName;

    @NotBlank(message = "Second Name should'nt be blank")
    private String secondName;

    @NotBlank(message = "Address should'nt be blank")
    private String address;

    @NotBlank(message = "sex should'nt be blank")
    private String sex;

    @NotBlank(message = "Height should'nt be blank")
    private String height;

    @NotBlank(message = "Eye color should'nt be blank")
    private String eyes;

    @NotNull(message = "Issued Date should'nt be blank")
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date issued;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(Date dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public Date getIssued() {
        return issued;
    }

    public void setIssued(Date issued) {
        this.issued = issued;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

}

