package com.meal.list.backend.entity;

import com.meal.list.backend.service.dishservice.DishCategoryEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private DishCategoryEnum categoryEnum;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ingredient_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Map<Ingredient, Weight> ingredients;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> descriptions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) &&
                Objects.equals(name, dish.name) &&
                categoryEnum == dish.categoryEnum &&
                Objects.equals(ingredients, dish.ingredients) &&
                Objects.equals(descriptions, dish.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryEnum, ingredients, descriptions);
    }
}
