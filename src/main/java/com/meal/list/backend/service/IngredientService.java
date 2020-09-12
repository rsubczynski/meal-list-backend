package com.meal.list.backend.service;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.error.exception.IngredientNotFoundException;
import com.meal.list.backend.repository.IngredientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredient() {
        return Optional.of(ingredientRepository.findAll()).orElse(new ArrayList<>());
    }

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient foundedItem = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
        ingredient.setId(id);
        BeanUtils.copyProperties(ingredient, foundedItem);
        return ingredientRepository.save(foundedItem);
    }

    public Long deleteById(Long id) {
        ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
        ingredientRepository.deleteById(id);
        return id;
    }
}
