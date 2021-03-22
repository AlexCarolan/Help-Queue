package com.example.helpqueue.restservice.services;

import com.example.helpqueue.restservice.repositories.TicketRepository;
import com.example.helpqueue.restservice.repositories.UserRepository;
import com.example.helpqueue.restservice.resources.Ticket;
import com.example.helpqueue.restservice.resources.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private TicketRepository repository;
    private UserRepository userRepository;

    public TicketService(TicketRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository  = userRepository;
    }

    public Ticket create(Ticket ticket) {
        if(userRepository.findById(ticket.getCreator().getId()).isPresent()) {
            return repository.save(ticket);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user was found for given creator id");
        }
    }

    public Ticket findById(Long id) {
        Optional<Ticket> ticket = this.repository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Ticket> findAll() {
        return this.repository.findAll();
    }
//
//    public Ticket updateTicket(Ticket newTicket, Long id) {
//    }
//
//    public void deleteById(Long id) {
//    }
}
