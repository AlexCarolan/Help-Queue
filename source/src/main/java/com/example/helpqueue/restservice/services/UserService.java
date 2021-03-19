package com.example.helpqueue.restservice.services;

import com.example.helpqueue.restservice.repositories.UserRepository;
import com.example.helpqueue.restservice.resources.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    public User findById(Long id) {
        Optional<User> user = this.repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public User updateUser(User newUser, Long id) {
        Optional<User> response = repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setAdmin(newUser.isAdmin());
                    return repository.save(user);
                });
        if (response.isPresent()) {
            return response.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteById(Long id) {
        if (this.repository.findById(id).isPresent()) {
            this.repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
