package com.meal.list.backend.service.dishservice;

import com.meal.list.backend.entity.Dish;
import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.entity.Weight;
import com.meal.list.backend.error.exception.DishListIsEmptyException;
import com.meal.list.backend.error.exception.DishNotFoundException;
import com.meal.list.backend.payload.DishResponse;
import com.meal.list.backend.payload.IngredientsListResponse;
import com.meal.list.backend.payload.MakroDishResponse;
import com.meal.list.backend.payload.DishSummaryResponse;
import com.meal.list.backend.repository.DishRepository;
import com.meal.list.backend.service.dishcalculateservice.DishCalculateService;
import com.meal.list.backend.service.dishcalculateservice.dishcalcstrategy.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishCalculateService calculateService;

    public List<MakroDishResponse> getAllDishes() {
        return Optional.ofNullable(dishRepository.findAll()).orElse(Collections.emptyList())
                .stream().map(this::buildMakroDish).collect(Collectors.toList());
    }

    private MakroDishResponse buildMakroDish(Dish dish) {
        return MakroDishResponse.builder()
                .id(dish.getId())
                .name(dish.getName())
                .type(dish.getCategoryEnum().toString())
                .protein(calculateService.calculate(DishCalculateEnum.PROTEIN, dish.getIngredients()))
                .carbohydrate(calculateService.calculate(DishCalculateEnum.CARBOHYDRATE, dish.getIngredients()))
                .fat(calculateService.calculate(DishCalculateEnum.FAT, dish.getIngredients()))
                .kcal(calculateService.calculate(DishCalculateEnum.KCAL, dish.getIngredients()))
                .build();
    }

    public DishResponse getDish(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException(id));
        return DishResponse
                .builder()
                .makroDish(buildMakroDish(dish))
                .descriptions(dish.getDescriptions())
                .ingredientsList(convertIngredientsToDisplay(dish.getIngredients()))
                .build();
    }

    private List<IngredientsListResponse> convertIngredientsToDisplay(Map<Ingredient, Weight> ingredientWeightMap) {
        return ingredientWeightMap.entrySet()
                .stream()
                .map(this::buildIngredientsListResponse)
                .collect(Collectors.toList());
    }

    private IngredientsListResponse buildIngredientsListResponse(Map.Entry<Ingredient, Weight> entry) {
        return IngredientsListResponse
                .builder()
                .name(entry.getKey().getName())
                .gram(entry.getValue().getGram())
                .build();
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
                .orElseThrow(() -> new DishListIsEmptyException(dishCategoryEnum.toString()))
                .stream()
                .map(Dish::getId)
                .collect(Collectors.toList());
        return ids.get(new Random().nextInt(ids.size()));
    }

    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish dish) {
        Dish foundedDish = dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException(id));
        dish.setId(id);
        BeanUtils.copyProperties(dish, foundedDish);
        return dishRepository.save(foundedDish);
    }

    public Long deleteById(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        dishRepository.delete(dish);
        return id;
    }
}
