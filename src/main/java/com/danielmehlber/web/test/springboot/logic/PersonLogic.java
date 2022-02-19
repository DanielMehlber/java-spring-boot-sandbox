package com.danielmehlber.web.test.springboot.logic;

import com.danielmehlber.web.test.springboot.Application;
import com.danielmehlber.web.test.springboot.db.PersonRepository;
import com.danielmehlber.web.test.springboot.entities.Person;
import com.danielmehlber.web.test.springboot.exceptions.NoSuchPersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("PersonLogic")
public class PersonLogic {

    @Autowired
    private PersonRepository personRepository;

    public void addPerson(final Person person) {
        personRepository.save(person);
    }

    public Person getPerson(final long id) throws NoSuchPersonException {
        return personRepository.findById(id).orElseThrow(() -> new NoSuchPersonException(id));
    }

}
