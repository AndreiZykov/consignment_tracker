package com.pawntracker.Entity;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 10, max = 16, message = "Wrong phone Number")
    @Column(updatable = false)
    private String number;

    public PhoneNumber() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
