package com.example.helpqueue.restservice.resources;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;
    @NotBlank
    private final boolean admin;
    @NotBlank
    private final String name;

    public User(long id, boolean admin, String name) {
        super();
        this.id = id;
        this.admin = admin;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }
}
