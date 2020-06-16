package com.meal.list.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

//    private List<Ingredient> ingredients;

    @ElementCollection(targetClass=String.class)
    private List<String> descriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }
}
