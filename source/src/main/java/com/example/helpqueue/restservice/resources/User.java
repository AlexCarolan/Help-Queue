package com.example.helpqueue.restservice.resources;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @NotBlank
    private boolean admin;
    @NotBlank
    private String name;
    @OneToMany(targetEntity=Ticket.class, mappedBy="creator",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> user = new ArrayList<>();

    public User() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
