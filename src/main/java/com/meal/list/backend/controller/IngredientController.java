package com.meal.list.backend.controller;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.service.ExcelParserService;
import com.meal.list.backend.service.IngredientService;
import com.meal.list.backend.service.filestorange.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping("apiV1/")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ExcelParserService excelParserService;

    @GetMapping(value = "ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<List<Ingredient>> getAllIngredients(){
        return ResponseEntity.ok(ingredientService.getAllIngredient());
    }

    @GetMapping(value = "ingredient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Ingredient> getIngredient(@PathVariable(required = true) Long id){
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    @PostMapping(value = "ingredients")
    public ResponseEntity<Set<Ingredient>> ingredient(@RequestParam("file") MultipartFile file) throws IOException {
        Set<Ingredient> ingredientSet = excelParserService.parseIngredient(fileService.getInputStream(file));
        ingredientService.saveAllIngredient(ingredientSet);
        return ResponseEntity.ok(ingredientSet);
    }

    @PostMapping(value = "ingredient")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.saveIngredient(ingredient));
    }
}
