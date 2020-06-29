package com.meal.list.backend.service.dishservice;

import com.meal.list.backend.entity.Dish;
import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.entity.Weight;
import com.meal.list.backend.error.exception.DishNotFoundException;
import com.meal.list.backend.payload.DishList;
import com.meal.list.backend.payload.DishSummaryResponse;
import com.meal.list.backend.repository.DishRepository;
import com.meal.list.backend.service.dishservice.dishcalcstrategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<DishList> getAllDishes() {
        return Optional.ofNullable(dishRepository.findAll()).orElse(Collections.emptyList())
                .stream().map(dish -> DishList.builder()
                        .id(dish.getId())
                        .name(dish.getName())
                        .type(dish.getCategoryEnum().toString())
                        .protein(calculate(DishCalculateEnum.PROTEIN, dish.getIngredients()))
                        .carbohydrate(calculate(DishCalculateEnum.CARBOHYDRATE, dish.getIngredients()))
                        .fat(calculate(DishCalculateEnum.FAT, dish.getIngredients()))
                        .kcal(calculate(DishCalculateEnum.KCAL, dish.getIngredients()))
                        .build()).collect(Collectors.toList());
    }

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
