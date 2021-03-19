package com.example.helpqueue.restservice.services;

import com.example.helpqueue.restservice.repositories.UserRepository;
import com.example.helpqueue.restservice.resources.User;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return this.repository.save(user);
    }

    public Optional<User> findById(Long id) {
         return this.repository.findById(id);
    }
}
