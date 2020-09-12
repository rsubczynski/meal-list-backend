package com.meal.list.backend.error.exception;

public class IngredientExistException extends RuntimeException {

    public IngredientExistException(String name) {
        super("Ingredient by name " + name + " is exist");
    }

    public IngredientExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
