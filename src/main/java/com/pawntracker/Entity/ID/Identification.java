package com.pawntracker.Entity.ID;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawntracker.Entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    //Implement for choosing identification type
    // Passport, ID , DL
    @NotBlank
    public  String type;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "identification")
    @JsonIgnore
    private User user;

    public Identification() {
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
