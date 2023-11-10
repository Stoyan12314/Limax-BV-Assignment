package org.example.buisness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CloudinaryException extends ResponseStatusException {
    public CloudinaryException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
