package com.example.helpqueue.restservice.controllers;

import com.example.helpqueue.restservice.resources.User;
import com.example.helpqueue.restservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    public UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users/create")
    ResponseEntity<User> create (@RequestBody User user){
        return new ResponseEntity<>(this.service.create(user), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping("/users/all")
    List<User> getAll() {
        return this.service.findAll();
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return this.service.updateUser(newUser, id);
    }

}
