package com.pawntracker.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "description cannot be empty")
    private String description;

    @NotBlank(message = "Price cannot be empty")
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_item_id", nullable = false)
    @JsonIgnore
    private User user;

    @Lob
    private Byte[] image;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Byte[] getImage() {
        return image;
    }



    public void setImage(Byte[] image) {
        this.image = image;
    }
}
