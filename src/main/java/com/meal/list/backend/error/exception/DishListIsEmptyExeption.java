package com.meal.list.backend.error.exception;

public class DishListIsEmptyExeption extends RuntimeException {
    public DishListIsEmptyExeption(String category) {
        super("Dish category enum is empty,  Category = "+ category);
    }

    public DishListIsEmptyExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
