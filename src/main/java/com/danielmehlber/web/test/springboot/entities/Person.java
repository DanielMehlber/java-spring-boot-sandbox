package com.danielmehlber.web.test.springboot.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String phrase;
    private int age;

    @JsonCreator
    public Person(@JsonProperty(required = false) final long id,
                  @JsonProperty(required = true) final String firstname,
                  @JsonProperty(required = true) final String lastname,
                  @JsonProperty(required = true) final int age,
                  @JsonProperty(required = true) final String phrase
    ) {
        this(firstname, lastname, age, phrase);
        this.id = id;
    }

    public Person(final String firstname, final String lastname, final int age, final String phrase) {
        this.age = age;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phrase = phrase;
    }
}
