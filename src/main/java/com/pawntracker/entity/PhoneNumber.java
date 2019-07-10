package com.pawntracker.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_phonenumber_id", updatable = false, nullable = false)
    @JsonIgnore
    private User users;

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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
