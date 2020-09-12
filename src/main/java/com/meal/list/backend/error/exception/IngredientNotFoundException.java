package com.meal.list.backend.error.exception;

public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException(Long id){
        super("Cannot find Ingredient with id " + id);
    }
}
