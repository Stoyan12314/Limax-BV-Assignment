package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailExistException extends ResponseStatusException {
    public EmailExistException() {
        super(HttpStatus.CONFLICT);
    }
}
