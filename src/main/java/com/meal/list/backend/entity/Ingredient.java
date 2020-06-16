package com.meal.list.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Double.compare(that.protein, protein) == 0 &&
                Double.compare(that.carbohydrate, carbohydrate) == 0 &&
                Double.compare(that.fat, fat) == 0 &&
                Double.compare(that.kcal, kcal) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, protein, carbohydrate, fat, kcal);
    }
}
