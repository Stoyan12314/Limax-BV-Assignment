package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundExerciseException extends ResponseStatusException {
    public NotFoundExerciseException() {
        super(HttpStatus.NOT_FOUND);
    }

}
