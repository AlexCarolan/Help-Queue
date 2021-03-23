package com.example.helpqueue.restservice.controllers;

import com.example.helpqueue.restservice.resources.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Bill")))
                .andExpect(jsonPath("$.admin", Matchers.is(true)));
    }

    @Test
    void getAll() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.get("/users/");
//        MvcResult result = mockMvc.perform(request)
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String json = result.getResponse().getContentAsString();
//        User[] user = new ObjectMapper().readValue(json, User[].class);
//
//        System.out.println(json + "\n\n\n here \n\n\n");
//
//        assertEquals(user[0].getId(), 1);
//        assertEquals(user[0].getName(), "Bill");
//        assertTrue(user[0].isAdmin());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}