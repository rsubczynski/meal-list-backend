package com.meal.list.backend.entity;

import com.meal.list.backend.service.dishservice.DishCategoryEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@NamedEntityGraph(name = "dish-get-all",
        attributeNodes = {
                @NamedAttributeNode("ingredients"),
                @NamedAttributeNode("descriptions"),
        }
)
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide a name")
    @Column( unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private DishCategoryEnum categoryEnum;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "ingredient_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Map<Ingredient, Weight> ingredients;


    @ElementCollection(targetClass = String.class)
    private List<String> descriptions;

}
