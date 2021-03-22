package com.example.helpqueue.restservice.repositories;

import com.example.helpqueue.restservice.resources.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, Long> {
}
