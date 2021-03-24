package com.example.helpqueue.restservice.controllers;

import com.example.helpqueue.restservice.resources.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
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

@ComponentScan(basePackages = {"com.example.helpqueue.*"})
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {

        User user = new User();
        user.setAdmin(false);
        user.setName("John");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        RequestBuilder request = MockMvcRequestBuilders.post("/users/create").contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(request)
                .andExpect(status().isCreated());

        request = MockMvcRequestBuilders.get("/users/3");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(3)))
                .andExpect(jsonPath("$.name", Matchers.is("John")))
                .andExpect(jsonPath("$.admin", Matchers.is(false)));
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
        RequestBuilder request = MockMvcRequestBuilders.get("/users/all");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Bill")))
                .andExpect(jsonPath("$[0].admin", Matchers.is(true)))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].name", Matchers.is("Alex")))
                .andExpect(jsonPath("$[1].admin", Matchers.is(false)));
    }

    @Test
    void updateUser() throws Exception {
        User user = new User();
        user.setAdmin(true);
        user.setName("Steve");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        RequestBuilder request = MockMvcRequestBuilders.put("/users/2").contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(request)
                .andExpect(status().isOk());

        request = MockMvcRequestBuilders.get("/users/2");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(2)))
                .andExpect(jsonPath("$.name", Matchers.is("Steve")))
                .andExpect(jsonPath("$.admin", Matchers.is(true)));

    }

    @Test
    void deleteUser() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/users/99");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());

        request = MockMvcRequestBuilders.delete("/users/1");
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}