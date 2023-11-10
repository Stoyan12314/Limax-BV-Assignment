package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }
}
