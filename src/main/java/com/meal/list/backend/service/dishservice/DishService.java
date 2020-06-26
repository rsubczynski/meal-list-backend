package com.meal.list.backend.service.dishservice;

import com.meal.list.backend.entity.Dish;
import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.entity.Weight;
import com.meal.list.backend.error.exception.DishNotFoundException;
import com.meal.list.backend.payload.DishResponse;
import com.meal.list.backend.payload.DishSummaryResponse;
import com.meal.list.backend.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<DishResponse> getAllDishes() {
        return Optional.ofNullable(dishRepository.findAll()).orElse(Collections.emptyList())
                .stream().map(dish -> DishResponse.builder()
                        .id(dish.getId())
                        .name(dish.getName())
                        .type(dish.getCategoryEnum().toString())
                        .protein(calculateProtein(dish.getIngredients()))
                        .carbohydrate(calculateCarbohydrate(dish.getIngredients()))
                        .fat(calculateFat(dish.getIngredients()))
                        .kcal(calculateKcal(dish.getIngredients()))
                        .build()).collect(Collectors.toList());
    }

    private double calculateProtein(Map<Ingredient, Weight> ingredientsWeightMap) {
        return ingredientsWeightMap.entrySet().stream().mapToDouble(map ->
                (map.getValue().getGram() * map.getKey().getProtein()) / 100).sum();
    }

    private double calculateCarbohydrate(Map<Ingredient, Weight> ingredientsWeightMap) {
        return ingredientsWeightMap.entrySet().stream().mapToDouble(map ->
                (map.getValue().getGram() * map.getKey().getCarbohydrate()) / 100).sum();
    }

    private double calculateFat(Map<Ingredient, Weight> ingredientsWeightMap) {
        return ingredientsWeightMap.entrySet().stream().mapToDouble(map ->
                (map.getValue().getGram() * map.getKey().getFat() / 100)).sum();
    }

    private double calculateKcal(Map<Ingredient, Weight> ingredientsWeightMap) {
        return ingredientsWeightMap.entrySet().stream().mapToDouble(map ->
                (map.getValue().getGram() * map.getKey().getKcal() / 100)).sum();
    }

    public Dish getDish(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException("Not found Dish on id = " + id));
    }

    public List<DishSummaryResponse> getCategorySummaryCount() {
        return Optional.ofNullable(dishRepository.findAll()).orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(Dish::getCategoryEnum))
                .entrySet().stream()
                .map((k) -> DishSummaryResponse.builder().categoryEnum(k.getKey()).count(k.getValue().size()).build())
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
