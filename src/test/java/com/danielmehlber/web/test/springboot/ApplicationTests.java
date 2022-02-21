package com.danielmehlber.web.test.springboot;

import com.danielmehlber.web.test.springboot.entities.Person;
import com.danielmehlber.web.test.springboot.logic.PersonLogic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    private MockMvc client;

    @Autowired
    private PersonLogic personLogic;

    @Test
    public void testUnknownPersonFetch() throws Exception {
        client.perform(get("/person/id/9999")).andExpect(status().isNoContent());
    }

    @Test
    public void testKnownPersonFetch() throws Exception {
        // prepare
        Person person = new Person("Daniel", "Mehlber", 20, "I love programming");
        personLogic.addPerson(person);

        // validate
        client.perform(get(String.format("/person/id/%d", person.getId())))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(person)));
    }

    @Test
    public void testAddPersonWithValidJson() throws Exception {
        // prepare
        Person person = new Person("Daniel", "Mehlber", 20, "I love programming");
        String personJson = new ObjectMapper().writeValueAsString(person);

        // perform
        String response = client.perform(post("/person/add").content(personJson).contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // validate
        long id = Long.parseLong(response);
        Person fetched = personLogic.getPerson(id);

        assertThat(fetched.equalsWithoutId(person)).isTrue();
    }

    @Test
    public void testAddPersonWithEmptyJson() throws Exception {
        // prepare
        String personJson = "{}";

        // perform
        client.perform(post("/person/add").content(personJson).contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

}
