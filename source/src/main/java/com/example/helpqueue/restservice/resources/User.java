package com.example.helpqueue.restservice.resources;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private final long id;
    private final boolean admin;
    private final String name;

    public User(long id, boolean admin, String name) {
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
