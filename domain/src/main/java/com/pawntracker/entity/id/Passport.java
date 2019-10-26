package com.pawntracker.entity.id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Passport {

    public Passport() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="identification_passport_id", nullable = false)
    @JsonIgnore
    private Identification identification;


    @NotBlank(message = "Type should'nt be blank")
    private String type;

    @NotBlank(message = "Code should'nt be blank")
    private String code;

    @NotBlank(message = "PassportNo should'nt be blank")
    private String passportNo;

    @NotBlank(message = "Suranme should'nt be blank")
    private String surname;

    @NotBlank(message = "Given Name should'nt be blank")
    private String givenName;

    @NotBlank(message = "Nationality should'nt be blank")
    private String nationality;

    @NotNull(message = "Date Of Birth should'nt be blank")
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date dateOfBirth;

    @NotBlank(message = "Palce Of Birth should'nt be blank")
    private String placeOfBirth;

    @NotNull(message = "Date of issue should'nt be blank")
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date dateOfIssue;

    @NotNull(message = "Date of expiration should'nt be blank")
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date dateOfExpiration;

    @NotBlank(message = "Sex should'nt be blank")
    private String sex;

    @NotBlank(message = "Authority should'nt be blank")
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(Date dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }
}
