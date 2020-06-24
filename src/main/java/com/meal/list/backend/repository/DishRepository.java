package com.meal.list.backend.repository;

import com.meal.list.backend.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {

}
