package com.meal.list.backend.repository;

import com.meal.list.backend.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
