package com.example.helpqueue.restservice.controllers;

import com.example.helpqueue.restservice.resources.User;
import com.example.helpqueue.restservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user/create")
    ResponseEntity<User> create (@RequestBody User user){
        return new ResponseEntity<>(this.service.create(user), HttpStatus.CREATED);
    }
}
