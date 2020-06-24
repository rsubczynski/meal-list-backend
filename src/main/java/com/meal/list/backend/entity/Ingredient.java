package com.meal.list.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column( unique = true)
    private String name;
    private double protein;
    private double carbohydrate;
    private double fat;
    private double kcal;

    @ManyToMany
    private Set<Dish> dishes;


}
