package com.meal.list.backend.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

}
