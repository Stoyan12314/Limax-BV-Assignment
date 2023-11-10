package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UpdateExerciseException extends ResponseStatusException {

    public UpdateExerciseException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
