package com.meal.list.backend.error.exception;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long id) {
        super("Not found Dish on id = " + id);
    }

    public DishNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
