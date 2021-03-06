package com.meal.list.backend.service.dishcalculateservice.dishcalcstrategy;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.entity.Weight;

import java.util.Map;

public class DishFatCalculate implements DishCalculate {

    @Override
    public double calculateSum(Map<Ingredient, Weight> ingredientsWeightMap) {
        return ingredientsWeightMap.entrySet().stream().mapToDouble(map ->
                (map.getValue().getGram() * map.getKey().getFat() / 100)).sum();
    }
}
