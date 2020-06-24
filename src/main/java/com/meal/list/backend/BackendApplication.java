package com.meal.list.backend;

import com.meal.list.backend.configuration.FileStorageProperties;
import com.meal.list.backend.entity.Dish;
import com.meal.list.backend.entity.Ingredient;
import com.meal.list.backend.entity.Weight;
import com.meal.list.backend.service.IngredientService;
import com.meal.list.backend.service.dishservice.DishCategoryEnum;
import com.meal.list.backend.service.dishservice.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private DishService dishService;

    @Autowired
    private IngredientService ingredientService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Ingredient> x = ingredientService.getAllIngredient();

        HashMap map = new HashMap();
        map.put(x.get(1), Weight.builder().gram(100).build());

        dishService.addDish(Dish.builder()
				.name("Jajecznica")
                .categoryEnum(DishCategoryEnum.BREAKFAST)
                .ingredients(map)
                .descriptions(Arrays.asList("Wbić jajka", "mieszać"))
                .build());

       List<Dish> a = dishService.getAllDishes();

    }
}
