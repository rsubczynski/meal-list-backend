package com.meal.list.backend.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


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

    @NotBlank(message = "Please provide a name")
    @Column( unique = true)
    private String name;
    private double protein;
    private double carbohydrate;
    private double fat;
    private double kcal;

}
