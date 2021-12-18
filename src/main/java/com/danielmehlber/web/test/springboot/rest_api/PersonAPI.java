package com.danielmehlber.web.test.springboot.rest_api;

import com.danielmehlber.web.test.springboot.entities.Person;
import com.danielmehlber.web.test.springboot.exceptions.NoSuchPersonException;
import com.danielmehlber.web.test.springboot.logic.PersonLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PersonAPI {

    @Resource(name="PersonLogic") PersonLogic personLogic;

    @PostMapping("/person/add")
    public void addPerson(@RequestBody final Person person) {
        personLogic.addPerson(person);
    }

    @GetMapping("/person/id/{id}")
    public Person getPerson(@PathVariable final int id) throws NoSuchPersonException {
        return personLogic.getPerson(id);
    }

}
