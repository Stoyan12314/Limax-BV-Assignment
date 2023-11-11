package org.example.buisness.exceptions;

public class FarmerNotFoundException extends RuntimeException{
    public FarmerNotFoundException(String message) {
        super(message);
    }
}
