package com.meal.list.backend.error.exception;

public class IngredientExistException extends RuntimeException {

    public IngredientExistException(String message) {
        super(message);
    }

    public IngredientExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
