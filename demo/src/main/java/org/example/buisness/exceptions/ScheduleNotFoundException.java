package org.example.buisness.exceptions;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
