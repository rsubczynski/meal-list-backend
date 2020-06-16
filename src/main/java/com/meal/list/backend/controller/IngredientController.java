package com.meal.list.backend.controller;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/ingredient")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<List<Ingredient>> getAllIngredients(){
        return ResponseEntity.ok(ingredientService.getAllIngredient());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Ingredient> getIngredient(@PathVariable(required = true) Long id){
        return ResponseEntity.ok(ingredientService.findById(id));
    }

}
