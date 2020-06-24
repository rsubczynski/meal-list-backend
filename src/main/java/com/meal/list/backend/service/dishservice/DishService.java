package com.meal.list.backend.service.dishservice;

import com.meal.list.backend.entity.Dish;
import com.meal.list.backend.error.exception.DishNotFoundException;
import com.meal.list.backend.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return Optional.of(dishRepository.findAll()).orElse(new ArrayList<>());
    }

    public Dish getDish(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException( "Not found Dish on id = " + id));
    }

    public Object getCategoryDishesCount() {
        return null;
    }

    public void addDish(Dish dish){
        dishRepository.save(dish);
    }
}
