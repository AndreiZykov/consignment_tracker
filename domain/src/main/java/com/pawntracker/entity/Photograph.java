package com.pawntracker.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Photograph {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String profilePhotograph;

    private String frontPhotograph;

    private ArrayList<String> photoHistory = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "photograph")
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfilePhotograph() {
        return profilePhotograph;
    }

    public void setProfilePhotograph(String profilePhotograph) {
        this.profilePhotograph = profilePhotograph;
    }

    public String getFrontPhotograph() {
        return frontPhotograph;
    }

    public void setFrontPhotograph(String frontPhotograph) {
        this.frontPhotograph = frontPhotograph;
    }

    public ArrayList<String> getPhotoHistory() {
        return photoHistory;
    }

    public void setPhotoHistory(ArrayList<String> photoHistory) {
        this.photoHistory = photoHistory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
