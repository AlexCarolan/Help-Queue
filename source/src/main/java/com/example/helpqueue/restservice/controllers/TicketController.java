package com.example.helpqueue.restservice.controllers;

import com.example.helpqueue.restservice.resources.Ticket;
import com.example.helpqueue.restservice.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    public TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping("/tickets/create")
    ResponseEntity<Ticket> create (@RequestBody Ticket ticket){
        this.service.create(ticket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/tickets/{id}")
    ResponseEntity<Ticket> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping("/tickets/all")
    List<Ticket> getAll() {
        return this.service.findAll();
    }
//
//    @PutMapping("/tickets/{id}")
//    Ticket updateTicket(@RequestBody Ticket newTicket, @PathVariable Long id) {
//        return this.service.updateTicket(newTicket, id);
//    }
//
//    @DeleteMapping("/tickets/{id}")
//    void deleteTicket(@PathVariable Long id) {
//        this.service.deleteById(id);
//    }
}
