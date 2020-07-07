package com.meal.list.backend.service.dishcalculateservice;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.entity.Weight;
import com.meal.list.backend.service.dishcalculateservice.dishcalcstrategy.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DishCalculateService {

    public double calculate(DishCalculateEnum calculateEnum, Map<Ingredient, Weight> weightMap) {
        DishCalculate calc;
        switch (calculateEnum) {
            case PROTEIN:
                calc = new DishProteinCalculate();
                break;
            case CARBOHYDRATE:
                calc = new DishCarboCalculate();
                break;
            case FAT:
                calc = new DishFatCalculate();
                break;
            case KCAL:
                calc = new DishKcalCalculate();
                break;
            default:
                throw new IllegalArgumentException("Unsupported enum " + calculateEnum);
        }
        return calc.calculateSum(weightMap);
    }
}
