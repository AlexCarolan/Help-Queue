package com.example.helpqueue.restservice.controllers;

import com.example.helpqueue.restservice.resources.Ticket;
import com.example.helpqueue.restservice.resources.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Timestamp;

@ComponentScan(basePackages = {"com.example.helpqueue.*"})
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {

        Timestamp timestamp = new Timestamp(1000000000000L);

        User user = new User();
        user.setId(1);

        Ticket ticket = new Ticket();
        ticket.setCreator(user);
        ticket.setTitle("Test");
        ticket.setDescription("Test description");
        ticket.setCreated(timestamp);
        ticket.setStatus("OPEN");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ticket);

        RequestBuilder request = MockMvcRequestBuilders.post("/tickets/create").contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(request)
                .andExpect(status().isCreated());

        request = MockMvcRequestBuilders.get("/tickets/3");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(3)))
                .andExpect(jsonPath("$.title", Matchers.is("Test")))
                .andExpect(jsonPath("$.description", Matchers.is("Test description")))
                .andExpect(jsonPath("$.creator.id", Matchers.is(1)))
                .andExpect(jsonPath("$.status", Matchers.is("OPEN")))
                .andExpect(jsonPath("$.created", Matchers.is("2001-09-09T01:46:40.000+00:00")));
    }

    @Test
    void getById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/tickets/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("Email")))
                .andExpect(jsonPath("$.description", Matchers.is("Email broken")))
                .andExpect(jsonPath("$.creator.id", Matchers.is(1)))
                .andExpect(jsonPath("$.status", Matchers.is("OPEN")))
                .andExpect(jsonPath("$.created", Matchers.is("2015-11-05T14:29:36.000+00:00")));
    }

    @Test
    void getAll() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/tickets/all");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Email")))
                .andExpect(jsonPath("$[0].description", Matchers.is("Email broken")))
                .andExpect(jsonPath("$[0].creator.id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].status", Matchers.is("OPEN")))
                .andExpect(jsonPath("$[0].created", Matchers.is("2015-11-05T14:29:36.000+00:00")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].title", Matchers.is("Login")))
                .andExpect(jsonPath("$[1].description", Matchers.is("Unable to login")))
                .andExpect(jsonPath("$[1].creator.id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].status", Matchers.is("CLOSED")))
                .andExpect(jsonPath("$[1].created", Matchers.is("2020-05-06T12:00:00.000+00:00")));
    }

    @Test
    void updateTicket() throws Exception {
        Timestamp timestamp = new Timestamp(1500000000000L);

        User user = new User();
        user.setId(2);

        Ticket ticket = new Ticket();
        ticket.setCreator(user);
        ticket.setTitle("Updated");
        ticket.setDescription("Updated description");
        ticket.setStatus("CLOSED");
        ticket.setCreated(timestamp);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ticket);

        RequestBuilder request = MockMvcRequestBuilders.put("/tickets/1").contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(request)
                .andExpect(status().isOk());

        request = MockMvcRequestBuilders.get("/tickets/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("Updated")))
                .andExpect(jsonPath("$.description", Matchers.is("Updated description")))
                .andExpect(jsonPath("$.creator.id", Matchers.is(2)))
                .andExpect(jsonPath("$.created", Matchers.is("2017-07-14T02:40:00.000+00:00")));

    }

    @Test
    void deleteTicket() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/tickets/99");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());

        request = MockMvcRequestBuilders.delete("/tickets/1");
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}