package com.meal.list.backend.error.exception;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {
        super(message);
    }

    public DishNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
