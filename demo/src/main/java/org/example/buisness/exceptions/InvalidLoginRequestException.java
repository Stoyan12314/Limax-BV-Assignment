package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidLoginRequestException extends ResponseStatusException {
    public InvalidLoginRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
