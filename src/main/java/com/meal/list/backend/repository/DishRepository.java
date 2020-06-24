package com.meal.list.backend.repository;

import com.meal.list.backend.entity.Dish;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {

    @EntityGraph(value = "dish-get-all")
    List<Dish> findAll();
}
