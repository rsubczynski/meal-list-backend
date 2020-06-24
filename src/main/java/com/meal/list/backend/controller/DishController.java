package com.meal.list.backend.controller;

import com.meal.list.backend.entity.Dish;
import com.meal.list.backend.service.dishservice.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apiV1/dish")
public class DishController {

    @Autowired
    private DishService dishService;

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    private @ResponseBody ResponseEntity<List<Dish>> getCategoryDishesCount(){
//        return ResponseEntity.ok(dishService.getCategoryDishesCount());
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<List<Dish>> getAllDishes(){
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Dish> getDish(@PathVariable(required = true) Long id){
        return ResponseEntity.ok(dishService.getDish(id));
    }
}
