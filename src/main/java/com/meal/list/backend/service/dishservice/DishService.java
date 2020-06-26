package com.meal.list.backend.service.dishservice;

import com.meal.list.backend.entity.Dish;
import com.meal.list.backend.error.exception.DishNotFoundException;
import com.meal.list.backend.payload.DishSummary;
import com.meal.list.backend.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return Optional.ofNullable(dishRepository.findAll()).orElse(Collections.emptyList());
    }

    public Dish getDish(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException("Not found Dish on id = " + id));
    }

    public List<DishSummary> getCategorySummaryCount() {
        return Optional.ofNullable(dishRepository.findAll()).orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(Dish::getCategoryEnum))
                .entrySet().stream()
                .map((k) -> DishSummary.builder().categoryEnum(k.getKey()).count(k.getValue().size()).build())
                .collect(Collectors.toList());
    }

    public Long getRandomDishIdByCategory(DishCategoryEnum dishCategoryEnum) {
        List<Long> ids = Optional.ofNullable(dishRepository.findAllByCategoryEnum(dishCategoryEnum))
                .orElseThrow(() -> new DishNotFoundException("The category has no dishes"))
                .stream()
                .map(Dish::getId)
                .collect(Collectors.toList());

        return ids.get(new Random().nextInt(ids.size()));
    }


    public void addDish(Dish dish) {
        dishRepository.save(dish);
    }
}
