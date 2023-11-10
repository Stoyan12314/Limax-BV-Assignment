package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExerciseNotFoundException extends ResponseStatusException {
    public ExerciseNotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }
}
