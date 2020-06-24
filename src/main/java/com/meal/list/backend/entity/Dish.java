package com.meal.list.backend.entity;

import com.meal.list.backend.service.dishservice.DishCategoryEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "ingredient_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

    @ElementCollection(targetClass = String.class)
    private List<String> descriptions;

}
