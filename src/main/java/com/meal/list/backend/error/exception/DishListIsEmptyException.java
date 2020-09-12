package com.meal.list.backend.error.exception;

public class DishListIsEmptyException extends RuntimeException {
    public DishListIsEmptyException(String category) {
        super("Dish category enum is empty,  Category = " + category);
    }

    public DishListIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
