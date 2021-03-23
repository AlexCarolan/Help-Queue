package com.example.helpqueue.restservice.controllers;

import com.example.helpqueue.restservice.resources.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"com.example.helpqueue.*"})
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() {
    }

    @Test
    void getById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/users/1");
        MvcResult result = mockMvc.perform(request).andReturn();

        String json = result.getResponse().getContentAsString();
        User user = new ObjectMapper().readValue(json, User.class);

        assertEquals(user.getId(), 1);
        assertEquals(user.getName(), "Bill");
        assertTrue(user.isAdmin());
    }

    @Test
    void getAll() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}