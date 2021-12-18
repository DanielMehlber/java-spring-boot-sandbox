package com.danielmehlber.web.test.springboot.db;

import com.danielmehlber.web.test.springboot.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
