package com.example.helpqueue.restservice.repositories;

import com.example.helpqueue.restservice.resources.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
