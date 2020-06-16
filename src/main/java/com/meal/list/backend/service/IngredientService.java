package com.meal.list.backend.service;

import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public void saveAllIngredient(Set<Ingredient> ingredientSet){
        ingredientSet
                .stream()
                .filter(ingredient -> !ingredientRepository.existByName(ingredient.getName()))
                .forEach(ingredientRepository::save);
    }

    public List<Ingredient> getAllIngredient(){
       return Optional.of(ingredientRepository.findAll()).orElse(new ArrayList<>());
    }

    public Ingredient findById(Long id){
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find Ingredient with id " + id));
    }
}
