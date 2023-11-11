package org.example.buisness.exceptions;

public class InventoryItemNotFoundException  extends RuntimeException{

    public InventoryItemNotFoundException(String message) {
        super(message);
    }
}
