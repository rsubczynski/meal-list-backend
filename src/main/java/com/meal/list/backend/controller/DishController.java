package com.meal.list.backend.controller;

import com.meal.list.backend.entity.Dish;
import com.meal.list.backend.payload.DishResponse;
import com.meal.list.backend.payload.MakroDishResponse;
import com.meal.list.backend.payload.DishSummaryResponse;
import com.meal.list.backend.service.dishservice.DishCategoryEnum;
import com.meal.list.backend.service.dishservice.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("apiV1/")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping(value = "dish/dishCategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<DishSummaryResponse>> getCategoryDishesCount() {
        return ResponseEntity.ok(dishService.getCategorySummaryCount());
    }

    @GetMapping(value = "dish", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MakroDishResponse>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping(value = "dish/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DishResponse> getDish(@PathVariable Long id) {
        return ResponseEntity.ok(dishService.getDish(id));
    }

    @GetMapping("dish/random")
    public ResponseEntity<Long> getRandomDishId(@RequestParam("type") DishCategoryEnum categoryEnum) {
        return ResponseEntity.ok(dishService.getRandomDishIdByCategory(categoryEnum));
    }

    @PostMapping(value = "dish", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> addIngredient(@Valid @RequestBody Dish dish){
        return ResponseEntity.ok(dishService.addDish(dish));
    }

    @PutMapping(value = "dish/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> updateIngredient(@PathVariable Long id, @Valid @RequestBody Dish dish){
        return ResponseEntity.ok(dishService.updateDish(id, dish));
    }

    @DeleteMapping(value = "dish/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Long> deleteDish(@PathVariable Long id){
        return ResponseEntity.ok(dishService.deleteById(id));
    }
}
