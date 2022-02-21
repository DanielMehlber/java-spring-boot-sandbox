package com.danielmehlber.web.test.springboot.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String phrase;
    private int age;

    /*
     * THIS CONSTRUCTOR IS MEANT TO BE PRIVATE
     * This default constructor is private because it is used to deserialize an object from JSON
     * and therefore is used only by the jackson library (which has private access to this class).
     * In any other scenario there is no use for a default constructor (with no arguments) and therefore
     * public access is prohibited.
     */
    private Person() {}

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

    public long getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getId() == person.getId() && getAge() == person.getAge() && Objects.equals(getFirstname(), person.getFirstname()) && Objects.equals(getLastname(), person.getLastname()) && Objects.equals(getPhrase(), person.getPhrase());
    }

    public boolean equalsWithoutId(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() && Objects.equals(getFirstname(), person.getFirstname()) && Objects.equals(getLastname(), person.getLastname()) && Objects.equals(getPhrase(), person.getPhrase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getPhrase(), getAge());
    }
}
