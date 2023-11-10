package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CreateExerciseException extends ResponseStatusException {
    public CreateExerciseException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
