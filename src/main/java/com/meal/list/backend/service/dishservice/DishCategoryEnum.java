package com.meal.list.backend.service.dishservice;

public enum DishCategoryEnum {
    BREAKFAST("Śniadanie"),
    LUNCH("Drugie śniadanie"),
    DINNER("Odiad"),
    SUPPER("Kolacja");

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
