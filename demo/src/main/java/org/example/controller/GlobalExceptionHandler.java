package org.example.controller;

import org.example.buisness.exceptions.ArticleNotFoundException;
import org.example.buisness.exceptions.FarmerNotFoundException;
import org.example.buisness.exceptions.InventoryItemNotFoundException;
import org.example.buisness.exceptions.ScheduleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.example.util.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleArticleNotFoundException(ArticleNotFoundException e) {
        ErrorResponse error = new ErrorResponse("Article Not Found", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FarmerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFarmerNotFoundException(FarmerNotFoundException e) {
        ErrorResponse error = new ErrorResponse("Farmer Not Found", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InventoryItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInventoryItemNotFoundException(InventoryItemNotFoundException e) {
        ErrorResponse error = new ErrorResponse("Inventory Item Not Found", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleScheduleNotFoundException(ScheduleNotFoundException e) {
        ErrorResponse error = new ErrorResponse("Schedule Not Found", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
