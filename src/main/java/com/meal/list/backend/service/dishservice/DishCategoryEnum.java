package com.meal.list.backend.service.dishservice;

public enum DishCategoryEnum {
    BREAKFAST("BREAKFAST"),
    LUNCH("LUNCH"),
    DINNER("DINNER"),
    SUPPER("SUPPER");

    private final String name;

    DishCategoryEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
