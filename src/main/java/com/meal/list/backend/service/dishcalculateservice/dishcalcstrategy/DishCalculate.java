package com.meal.list.backend.service.dishcalculateservice.dishcalcstrategy;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.entity.Weight;

import java.util.Map;

public interface DishCalculate {

    double calculateSum(Map<Ingredient, Weight> ingredientsWeightMap);
}
