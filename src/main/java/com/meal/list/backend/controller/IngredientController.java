package com.meal.list.backend.controller;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("apiV1/")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping(value = "ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody ResponseEntity<List<Ingredient>> getAllIngredients(){
        return ResponseEntity.ok(ingredientService.getAllIngredient());
    }

    @GetMapping(value = "ingredient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Ingredient> getIngredient(@PathVariable Long id){
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    @PostMapping(value = "ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.saveIngredient(ingredient));
    }

    @PutMapping(value = "ingredient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @Valid @RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredient));
    }

    @DeleteMapping(value = "ingredient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Long> deleteIngredient(@PathVariable Long id){
        return ResponseEntity.ok(ingredientService.deleteById(id));
    }
}
