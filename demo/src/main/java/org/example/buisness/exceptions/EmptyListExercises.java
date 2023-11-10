package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
public class EmptyListExercises extends ResponseStatusException {

    public EmptyListExercises() {
        super(HttpStatus.NOT_FOUND);
    }
}
