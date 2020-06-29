package com.meal.list.backend.service.dishservice.dishcalcstrategy;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.entity.Weight;

import java.util.Map;

public class DishKcalCalculate implements DishCalculate {

    @Override
    public double calculateSum(Map<Ingredient, Weight> ingredientsWeightMap) {
        return ingredientsWeightMap.entrySet().stream().mapToDouble(map ->
                (map.getValue().getGram() * map.getKey().getKcal() / 100)).sum();
    }
}
