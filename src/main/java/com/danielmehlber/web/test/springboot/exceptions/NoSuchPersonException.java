package com.danielmehlber.web.test.springboot.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoSuchPersonException extends Exception {

    private long personId;

    public NoSuchPersonException(final long id) {
        super(String.format("no person with id %d found in database", id));
        this.personId = id;
    }

}
