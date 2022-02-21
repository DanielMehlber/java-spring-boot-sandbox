package com.danielmehlber.web.test.springboot.rest_api;

import com.danielmehlber.web.test.springboot.entities.Person;
import com.danielmehlber.web.test.springboot.exceptions.NoSuchPersonException;
import com.danielmehlber.web.test.springboot.logic.PersonLogic;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PersonAPI {

    @Resource(name="PersonLogic") PersonLogic personLogic;

    @PostMapping(value = "/person/add", consumes = "application/json")
    public long addPerson(@RequestBody final Person person) {
        personLogic.addPerson(person);
        return person.getId();
    }

    @GetMapping("/person/id/{id}")
    public Person getPerson(@PathVariable final int id) throws NoSuchPersonException {
        return personLogic.getPerson(id);
    }

}
